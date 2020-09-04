package com.cristovantamayo.restfoodapi.domains.grupo.repository;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.grupo.model.Permissao;

public interface PermissaoRepository {
	List<Permissao> getAll();
	Permissao findById(Long id);
	Permissao save(Permissao permissao);
	void remove(Permissao permissao);

}
