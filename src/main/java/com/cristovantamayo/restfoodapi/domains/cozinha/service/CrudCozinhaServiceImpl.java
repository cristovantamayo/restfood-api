package com.cristovantamayo.restfoodapi.domains.cozinha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.cozinha.repository.CozinhaRepository;
import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Service
class CrudCozinhaServiceImpl implements CrudCozinhaService {
	
	CozinhaRepository repository;
	
	@Autowired
	public CrudCozinhaServiceImpl(CozinhaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Cozinha> listar(){
		return repository.findAll();
	}

	@Override
	public Optional<Cozinha> buscar(Long cozinhaId) {
		return repository.findById(cozinhaId);
	}
	
	@Override
	public List<Cozinha> buscarPorNome(String nome) {
		return repository.findCozinhasByNomeContains(nome);
	}
	
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}
	
	@Override
	public void excluir(Long cozinhaId) {
		
		repository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("O ID %d informado para Cozinha não existe.", cozinhaId)));
		
		try {
			repository.deleteById(cozinhaId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("A Cozinha de código %d não pode ser removida, pois está em uso.", cozinhaId));
		}
	}

	@Override
	public Boolean cozinhaExiste(String nome) {
		return repository.existsByNome(nome);
	}

}
