package com.cristovantamayo.restfoodapi.domains.cozinha.repository;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> getAll();
	Cozinha findById(Long id);
	Cozinha save(Cozinha cozinha);
	void remove(Long id);

}
