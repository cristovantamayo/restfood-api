package com.cristovantamayo.restfoodapi.domains.restaurante.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

@Repository
public interface RestauranteRepository
		extends JpaRepository<Restaurante, Long>, 
			RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	Integer countByCozinhaId(long cozinhaId);
}
