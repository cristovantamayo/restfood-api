package com.cristovantamayo.restfoodapi.domains.cozinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.cozinha.repository.CozinhaRepository;
import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;

@Service
class CrudCozinhaServiceImpl implements CrudCozinhaService {
	
	CozinhaRepository repository;
	
	@Autowired
	public CrudCozinhaServiceImpl(CozinhaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Cozinha> listar(){
		return repository.getAll();
	}

	@Override
	public Cozinha buscar(Long cozinhaId) {
		return repository.findById(cozinhaId);
	}
	
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}
	
	@Override
	public void excluir(Long cozinhaId) {
		try {
			repository.remove(cozinhaId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("A Cozinha de código %d não pode ser removida, pois está em uso.", cozinhaId));
		}
	}

}
