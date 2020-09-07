package com.cristovantamayo.restfoodapi.domains.restaurante.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

public interface CrudRestauranteService {
	
	List<Restaurante> listar();
	Optional<Restaurante> buscar(Long restauranteId);
	List<Restaurante> buscarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> buscarPorNomeDeCozinhaContendoETaxaFrete(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal);
	Restaurante salvar(Restaurante restaurante);
	void excluir(Long restauranteId);
	Integer contarPorCozinhas(Long cozinhaId);

}
