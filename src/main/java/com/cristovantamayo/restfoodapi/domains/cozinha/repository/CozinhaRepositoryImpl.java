package com.cristovantamayo.restfoodapi.domains.cozinha.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;

@Repository
class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> getAll(){
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	}
	
	@Override
	public Cozinha findById(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public Cozinha save(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		Cozinha cozinha = findById(id);
		
		if(cozinha == null)
			throw new EmptyResultDataAccessException(1);
		
		manager.remove(cozinha);
	}

}
