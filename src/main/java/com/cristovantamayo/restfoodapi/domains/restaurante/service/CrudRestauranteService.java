package com.cristovantamayo.restfoodapi.domains.restaurante.service;

import java.util.List;
import java.util.Optional;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

public interface CrudRestauranteService {
	
	List<Restaurante> listar();
	Optional<Restaurante> buscar(Long restauranteId);
	Restaurante salvar(Restaurante restaurante);
	void excluir(Long restauranteId);

}
