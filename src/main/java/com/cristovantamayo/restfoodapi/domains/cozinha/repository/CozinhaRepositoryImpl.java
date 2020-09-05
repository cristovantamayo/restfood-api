package com.cristovantamayo.restfoodapi.domains.cozinha.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

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
	public Cozinha findById(Long cozinhaId) {
		return manager.find(Cozinha.class, cozinhaId);
	}

	@Override
	public List<Cozinha> getContaining(String nome) {
		return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	@Transactional
	@Override
	public Cozinha save(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public void remove(Long cozinhaId) {
		Cozinha cozinha = findById(cozinhaId);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não foi encontrada uma Cozinha com ID '%d'", cozinhaId));
		}
		
		manager.remove(cozinha);
	}

}
