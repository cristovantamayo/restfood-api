package com.cristovantamayo.restfoodapi.api.estado.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;
import com.cristovantamayo.restfoodapi.domains.estado.service.CrudEstadoService;
import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private CrudEstadoService service;
	
	@GetMapping
	public List<Estado> listar() {
		return service.listar();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
		Estado estado = service.buscar(estadoId);
		
		if(estado == null)
			return ResponseEntity.notFound().build();
	
		return ResponseEntity.ok(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody Estado estado) {
		return service.salvar(estado);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
		Estado estadoAtual = service.buscar(estadoId);
		
		if(estadoAtual == null)
			return ResponseEntity.notFound().build();
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		estadoAtual = service.salvar(estadoAtual);
		return ResponseEntity.ok(estadoAtual);
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> excluir(@PathVariable Long estadoId) {
		try {
			service.excluir(estadoId);
			return ResponseEntity.noContent().build();
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
	
	
	
}
