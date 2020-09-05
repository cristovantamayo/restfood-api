package com.cristovantamayo.restfoodapi.api.cidade.controller;

import java.util.List;
import java.util.Optional;

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

import com.cristovantamayo.restfoodapi.domains.cidade.model.Cidade;
import com.cristovantamayo.restfoodapi.domains.cidade.service.CrudCidadesService;
import com.cristovantamayo.restfoodapi.domains.estado.repository.EstadoRepository;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	CrudCidadesService service;
	
	@Autowired
	EstadoRepository RepositoryEstado;
	
	@GetMapping
	public List<Cidade> listar(){
		return service.listar();
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade = service.buscar(cidadeId);
		
		if(!cidade.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(cidade.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> salvar(@RequestBody Cidade cidade){
		try {
			cidade = service.salvar(cidade);
			
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(cidade);
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId,
		@RequestBody Cidade cidade){
		
		Optional<Cidade> cidadeAtual = service.buscar(cidadeId);
		
		if(!cidadeAtual.isPresent())
			ResponseEntity.notFound().build();
		
		BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
		Cidade cidadeSalva = service.salvar(cidadeAtual.get());
		return ResponseEntity.ok(cidadeSalva);
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> excluir(@PathVariable Long cidadeId){
		try {
			service.excluir(cidadeId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}
	
}
