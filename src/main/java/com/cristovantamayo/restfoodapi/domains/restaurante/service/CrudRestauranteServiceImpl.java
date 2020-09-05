package com.cristovantamayo.restfoodapi.domains.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.cozinha.repository.CozinhaRepository;
import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;
import com.cristovantamayo.restfoodapi.domains.restaurante.repository.RestauranteRepository;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Service
class CrudRestauranteServiceImpl implements CrudRestauranteService {
	
	@Autowired
	RestauranteRepository repository;
	
	@Autowired
	CozinhaRepository repositoryCozinha;

	@Override
	public List<Restaurante> listar() {
		return repository.getAll();
	}

	@Override
	public Restaurante buscar(Long restauranteId) {
		return repository.findById(restauranteId);
	}

	@Override
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = repositoryCozinha.findById(cozinhaId);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		}
		
		restaurante.setCozinha(cozinha);
		return repository.save(restaurante);
	}

	@Override
	public void excluir(Long restauranteId) {
		repository.remove(restauranteId);
	}

}
