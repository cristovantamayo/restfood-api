package com.cristovantamayo.restfoodapi.domains.cidade.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domains.cidade.model.Cidade;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Repository
class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> getAll(){
		return manager.createQuery("from Cidade", Cidade.class)
				.getResultList();
	}
	
	@Override
	public Cidade findById(Long cidadeId) {
		return manager.find(Cidade.class, cidadeId);
	}
	
	@Transactional
	@Override
	public Cidade save(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	@Transactional
	@Override
	public void remove(Long cidadeId) {
		Cidade cidade = findById(cidadeId);
		
		if(cidade == null) {
			throw new EntidadeNaoEncontradaException(
				String.format("O ID %d informado não corresponde a uma Cidade válida", cidadeId));
		}
		
		manager.remove(cidade);
	}

}
