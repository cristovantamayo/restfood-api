package com.cristovantamayo.restfoodapi.api.restaurante.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;
import com.cristovantamayo.restfoodapi.domains.restaurante.repository.RestauranteRepository;
import com.cristovantamayo.restfoodapi.domains.restaurante.service.CrudRestauranteService;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	CrudRestauranteService service;
	
	@Autowired
	RestauranteRepository repository;
	
	@GetMapping
	public List<Restaurante> listar(){
		return service.listar();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Optional<Restaurante> restaurante = service.buscar(restauranteId);
		
		if(!restaurante.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(restaurante.get());
	}
	
	@GetMapping("/por-taxa-frete")
	public List<Restaurante> buscarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return service.buscarPorTaxaFrete(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/por-nome-cozinha-e-taxa-frete")
	public List<Restaurante> buscarPorNomeDeCozinhaContendoETaxaFrete(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal){
		return service.buscarPorNomeDeCozinhaContendoETaxaFrete(nomeCozinha, taxaInicial, taxaFinal);
	}
	
	@GetMapping("/count-por-cozinha")
	public Integer contarPorCozinhas(Long cozinhaId){
		return service.contarPorCozinhas(cozinhaId);
	}
	
	@GetMapping("/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nomeCozinha) {
		return repository.findComFreteGratis(nomeCozinha);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = service.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(restaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
		@RequestBody Restaurante restaurante){
		
		try {
			Optional<Restaurante> restauranteAtual = service.buscar(restauranteId);
			
			if(!restauranteAtual.isPresent())
				return ResponseEntity.notFound().build();
			
			BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
			Restaurante restauranteSalvo = service.salvar(restauranteAtual.get());
			return ResponseEntity.ok(restauranteSalvo);
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
		}
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> fieldsPayload){
	
		Optional<Restaurante> restauranteAtual = service.buscar(restauranteId);
		
		if(!restauranteAtual.isPresent())
			return ResponseEntity.notFound().build();
		
		merge(fieldsPayload, restauranteAtual.get());
		return atualizar(restauranteId, restauranteAtual.get());
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
	public ResponseEntity<?> excluir(@PathVariable Long restauranteId) {
		try {
			service.excluir(restauranteId);
			return ResponseEntity.noContent().build();
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}

}
