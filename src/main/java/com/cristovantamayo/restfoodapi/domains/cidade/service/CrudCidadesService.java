package com.cristovantamayo.restfoodapi.domains.cidade.service;

import java.util.List;
import java.util.Optional;

import com.cristovantamayo.restfoodapi.domains.cidade.model.Cidade;

public interface CrudCidadesService {
	List<Cidade> listar();
	Optional<Cidade> buscar(Long cidadeId);
	Cidade salvar(Cidade cidade);
	void excluir(Long cidadeId);
	Cidade getOrFail(Long cidadeId);
}
