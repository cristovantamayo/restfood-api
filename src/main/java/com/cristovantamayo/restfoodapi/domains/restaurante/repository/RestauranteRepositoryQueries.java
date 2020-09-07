package com.cristovantamayo.restfoodapi.domains.restaurante.repository;

import java.math.BigDecimal;
import java.util.List;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

}