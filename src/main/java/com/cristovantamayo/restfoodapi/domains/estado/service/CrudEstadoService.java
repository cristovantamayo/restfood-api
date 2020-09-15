package com.cristovantamayo.restfoodapi.domains.estado.service;

import java.util.List;
import java.util.Optional;

import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;

public interface CrudEstadoService {
	List<Estado> listar();
	Optional<Estado> buscar(Long estadoId);
	Estado salvar(Estado estado);
	void excluir(Long estadoId);
	Estado getOrFail(Long estadoId);
}
