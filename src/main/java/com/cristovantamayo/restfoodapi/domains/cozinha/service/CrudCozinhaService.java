package com.cristovantamayo.restfoodapi.domains.cozinha.service;

import java.util.List;
import java.util.Optional;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;

public interface CrudCozinhaService {
	List<Cozinha> listar();
	Optional<Cozinha> buscar(Long cozinhaId);
	List<Cozinha> buscarPorNome(String nome);
	Cozinha salvar(Cozinha cozinha);
	void excluir(Long cozinhaId);
	Boolean cozinhaExiste(String nome);
	Cozinha getOrFail(Long cozinhaId);
}
