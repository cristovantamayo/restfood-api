package com.cristovantamayo.restfoodapi.domains.cidade.service;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.cidade.model.Cidade;

public interface CrudCidadesService {
	List<Cidade> listar();
	Cidade buscar(Long cidadeId);
	Cidade salvar(Cidade cidade);
	void excluir(Long cidadeId);
}
