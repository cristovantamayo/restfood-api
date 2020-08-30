package com.cristovantamayo.restfoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domain.model.Cozinha;
import com.cristovantamayo.restfoodapi.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

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
	public void remove(Cozinha cozinha) {
		cozinha = findById(cozinha.getId());
		manager.remove(cozinha);
	}

}
