package com.cristovantamayo.restfoodapi.api.restaurante.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cristovantamayo.restfoodapi.domains.cozinha.exception.CozinhaNaoEncontradaException;
import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;
import com.cristovantamayo.restfoodapi.domains.restaurante.service.CrudRestauranteService;
import com.cristovantamayo.restfoodapi.exception.NegocioException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	CrudRestauranteService service;
	
	@GetMapping
	public List<Restaurante> listar(){
		return service.listar();
	}
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {
		return service.getOrFail(restauranteId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody Restaurante restaurante) {
		try {
			return service.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId,
		@RequestBody Restaurante restaurante){
		
		Restaurante restauranteAtual = service.getOrFail(restauranteId);
		BeanUtils.copyProperties(
			restaurante, 
			restauranteAtual, 
			"id", 
			"formasPagamento", 
			"endereco", 
			"createdAt",
			"produtos");
		
		try {
			return service.salvar(restauranteAtual);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> fieldsPayload){
	
		Restaurante restauranteAtual = service.getOrFail(restauranteId);
		merge(fieldsPayload, restauranteAtual);
		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> fieldsPayload, Restaurante restauranteTarget) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigin =
				objectMapper.convertValue(fieldsPayload, Restaurante.class);
		
		fieldsPayload.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, key);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigin);
			ReflectionUtils.setField(field, restauranteTarget, novoValor);
		});
	}
	
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long restauranteId) {
		service.excluir(restauranteId);
	}

}
