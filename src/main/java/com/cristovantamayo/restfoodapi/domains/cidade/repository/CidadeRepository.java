package com.cristovantamayo.restfoodapi.domains.cidade.repository;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.cidade.model.Cidade;

public interface CidadeRepository {
	List<Cidade> getAll();
	Cidade findById(Long cidadeId);
	Cidade save(Cidade cidade);
	void remove(Long cidadeId);
}
