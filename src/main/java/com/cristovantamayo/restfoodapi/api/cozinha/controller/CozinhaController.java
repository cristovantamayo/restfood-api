package com.cristovantamayo.restfoodapi.api.cozinha.controller;

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

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.cozinha.service.CrudCozinhaService;
import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CrudCozinhaService service;
	
	@GetMapping
	public List<Cozinha> listar() {
		return service.listar();
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = service.buscar(cozinhaId);
		
		if(cozinha == null)
			return ResponseEntity.notFound().build();
			
		return ResponseEntity.ok(cozinha);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return service.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
		
		Cozinha cozinhaAtual = service.buscar(cozinhaId);
		if(cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaAtual = service.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<?> excluir(@PathVariable Long cozinhaId) {
		try {
			service.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
			
		} catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(e.getMessage());
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(e.getMessage());
		}
		
	}
	
}
