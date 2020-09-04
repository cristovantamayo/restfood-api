package com.cristovantamayo.restfoodapi.domains.estado.repository;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;

public interface EstadoRepository {
	List<Estado> getAll();
	Estado findById(Long id);
	Estado save(Estado cidade);
	void remove(Estado cidade);

}
