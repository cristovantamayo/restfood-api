package com.cristovantamayo.restfoodapi.domains.restaurante.repository;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> getAll();
	Restaurante findById(Long id);
	Restaurante save(Restaurante restaurante);
	void remove(Long restauranteId);

}
