package com.cristovantamayo.restfoodapi.domains.cozinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.cozinha.exception.EntidadeEmUsoException;
import com.cristovantamayo.restfoodapi.domains.cozinha.exception.EntidadeNaoEncontradaException;
import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.cozinha.repository.CozinhaRepository;

@Service
class CrudCozinhaServiceImpl implements CrudCozinhaService {
	
	@Autowired
	CozinhaRepository repository;
	
	@Override
	public List<Cozinha> listar(){
		return repository.getAll();
	}
	
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}
	
	@Override
	public void excluir(Long cozinhaId) {
		try {
			repository.remove(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Cozinha de código %d não pode ser encontrada.", cozinhaId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso.", cozinhaId));
		}
	}

}
