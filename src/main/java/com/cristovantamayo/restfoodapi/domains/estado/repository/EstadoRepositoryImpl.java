package com.cristovantamayo.restfoodapi.domains.estado.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> getAll(){
		return manager.createQuery("from Estado", Estado.class)
				.getResultList();
	}
	
	@Override
	public Estado findById(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	@Override
	public Estado save(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public void remove(Estado estado) {
		estado = findById(estado.getId());
		manager.remove(estado);
	}

}
