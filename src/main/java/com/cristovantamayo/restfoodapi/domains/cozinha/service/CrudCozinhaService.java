package com.cristovantamayo.restfoodapi.domains.cozinha.service;

import java.util.List;
import java.util.Optional;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;

public interface CrudCozinhaService {
	List<Cozinha> listar();
	Optional<Cozinha> buscar(Long cozinhaId);
	Cozinha salvar(Cozinha cozinha);
	void excluir(Long cozinhaId);
}
