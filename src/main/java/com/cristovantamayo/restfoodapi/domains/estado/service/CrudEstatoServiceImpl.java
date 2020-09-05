package com.cristovantamayo.restfoodapi.domains.estado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;
import com.cristovantamayo.restfoodapi.domains.estado.repository.EstadoRepository;
import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Service
class CrudEstatoServiceImpl implements CrudEstadoService {

	EstadoRepository repository;
	
	@Autowired
	public CrudEstatoServiceImpl(EstadoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Estado> listar() {
		return repository.findAll(); 
	}

	@Override
	public Optional<Estado> buscar(Long estadoId) {
		return repository.findById(estadoId);
	}

	@Override
	public Estado salvar(Estado estado) {
		return repository.save(estado);
	}

	@Override
	public void excluir(Long estadoId) {
		
		repository.findById(estadoId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O ID %d informado para Restaurante não existe.", estadoId)));
		
		try {
			repository.deleteById(estadoId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("o Estado de código %d não pode ser removido, pois está em uso.", estadoId));
		}
	}

}
