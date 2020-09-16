package com.cristovantamayo.restfoodapi.domains.restaurante.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.cozinha.service.CrudCozinhaService;
import com.cristovantamayo.restfoodapi.domains.restaurante.exception.RestauranteNaoEncontradoException;
import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;
import com.cristovantamayo.restfoodapi.domains.restaurante.repository.RestauranteRepository;
import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;

@Service
class CrudRestauranteServiceImpl implements CrudRestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO = 
		"o Restaurante de código %d não pode ser removido, pois está em uso.";

	@Autowired
	RestauranteRepository repository;
	
	@Autowired
	CrudCozinhaService serviceCozinha;

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
		return repository.find(nomeCozinha, taxaInicial, taxaFinal);
	}

	@Override
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = serviceCozinha.getOrFail(cozinhaId);
		restaurante.setCozinha(cozinha);
		return repository.save(restaurante);
	}

	@Override
	public void excluir(Long restauranteId) {
		try {
			repository.deleteById(restauranteId);
			
		} catch(EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(restauranteId);
			
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
		}
	}

	@Override
	public Integer contarPorCozinhas(Long cozinhaId) {
		return repository.countByCozinhaId(cozinhaId);
	}

	@Override
	public Restaurante getOrFail(Long restauranteId) {
		return repository.findById(restauranteId)
			.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

}
