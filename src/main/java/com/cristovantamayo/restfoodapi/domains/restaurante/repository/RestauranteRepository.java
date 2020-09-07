package com.cristovantamayo.restfoodapi.domains.restaurante.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

@Repository
public interface RestauranteRepository
		extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//List<Restaurante> buscarPorCozinhaNomeETaxaFrete(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	Integer countByCozinhaId(long cozinhaId);
}
