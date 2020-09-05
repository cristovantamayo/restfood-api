package com.cristovantamayo.restfoodapi.domains.estado.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Repository
class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> getAll(){
		return manager.createQuery("from Estado", Estado.class)
				.getResultList();
	}
	
	@Override
	public Estado findById(Long estadoId) {
		return manager.find(Estado.class, estadoId);
	}
	
	@Transactional
	@Override
	public Estado save(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public void remove(Long estadoId) {
		Estado estado = findById(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaException(
				String.format("O ID %d informado não corresponde a um Estado válido", estadoId));
		}
		
		manager.remove(estado);
	}

}
