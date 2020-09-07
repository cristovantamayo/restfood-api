package com.cristovantamayo.restfoodapi.domains.cozinha.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cristovantamayo.restfoodapi.commons.repository.CustomJpaRepository;
import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	
	List<Cozinha> findCozinhasByNomeContains(String nome);
	Boolean existsByNome(String nome);

}
