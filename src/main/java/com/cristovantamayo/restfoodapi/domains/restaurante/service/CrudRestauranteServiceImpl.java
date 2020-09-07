package com.cristovantamayo.restfoodapi.domains.restaurante.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
		return repository.findAll();
	}

	@Override
	public Optional<Restaurante> buscar(Long restauranteId) {
		return repository.findById(restauranteId);
	}
	
	@Override
	public List<Restaurante> buscarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return repository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@Override
	public List<Restaurante> buscarPorNomeDeCozinhaContendoETaxaFrete(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		//return repository.buscarPorCozinhaNomeETaxaFrete(nomeCozinha, taxaInicial, taxaFinal);
		return repository.find(nomeCozinha, taxaInicial, taxaFinal);
	}

	@Override
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = repositoryCozinha.findById(cozinhaId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O ID %d informado para Cozinha não existe.", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		return repository.save(restaurante);
	}

	@Override
	public void excluir(Long restauranteId) {
		repository.findById(restauranteId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O ID %d informado para Restaurante não existe.", restauranteId)));
			
		repository.deleteById(restauranteId);
	}

	@Override
	public Integer contarPorCozinhas(Long cozinhaId) {
		return repository.countByCozinhaId(cozinhaId);
	}

}
