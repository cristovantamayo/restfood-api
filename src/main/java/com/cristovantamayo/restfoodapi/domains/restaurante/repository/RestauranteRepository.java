package com.cristovantamayo.restfoodapi.domains.restaurante.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cristovantamayo.restfoodapi.commons.repository.CustomJpaRepository;
import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

@Repository
public interface RestauranteRepository
		extends CustomJpaRepository<Restaurante, Long>, 
			RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
	
	@Query("FROM Restaurante r JOIN fetch r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	Integer countByCozinhaId(long cozinhaId);
}
