package com.cristovantamayo.restfoodapi.domain.repository;

import java.util.List;

import com.cristovantamayo.restfoodapi.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> getAll();
	Cozinha findById(Long id);
	Cozinha save(Cozinha cozinha);
	void remove(Cozinha cozinha);

}
