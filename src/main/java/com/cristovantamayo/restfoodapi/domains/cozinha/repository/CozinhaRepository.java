package com.cristovantamayo.restfoodapi.domains.cozinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	List<Cozinha> findCozinhasByNomeContains(String nome);
	Boolean existsByNome(String nome);

}
