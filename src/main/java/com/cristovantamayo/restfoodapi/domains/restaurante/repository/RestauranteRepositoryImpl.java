package com.cristovantamayo.restfoodapi.domains.restaurante.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Repository
class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> getAll(){
		return manager.createQuery("from Restaurante", Restaurante.class)
				.getResultList();
	}
	
	@Override
	public Restaurante findById(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Transactional
	@Override
	public Restaurante save(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Transactional
	@Override
	public void remove(Long restauranteId) {
		Restaurante restaurante = findById(restauranteId);
		
		if(restaurante == null) {
			throw new EntidadeNaoEncontradaException(
				String.format("O ID %d informado não corresponde a um Restaurante válido", restauranteId));
		}
		
		manager.remove(restaurante);
	}

}
