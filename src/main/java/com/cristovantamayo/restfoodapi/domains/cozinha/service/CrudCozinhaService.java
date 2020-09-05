package com.cristovantamayo.restfoodapi.domains.cozinha.service;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;

public interface CrudCozinhaService {
	List<Cozinha> listar();
	Cozinha buscar(Long cozinhaId);
	Cozinha salvar(Cozinha cozinha);
	void excluir(Long cozinhaId);
}
