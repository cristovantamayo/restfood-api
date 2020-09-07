package com.cristovantamayo.restfoodapi.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.cozinha.repository.CozinhaRepository;
import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;
import com.cristovantamayo.restfoodapi.domains.restaurante.repository.RestauranteRepository;
import com.cristovantamayo.restfoodapi.domains.restaurante.service.CrudRestauranteService;

@RestController
public class TestesController {

	@Autowired
	CrudRestauranteService service;
	
	@Autowired
	RestauranteRepository repositoryRestaurante;
	
	@Autowired
	CozinhaRepository repositoryCozinha;
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> buscarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return service.buscarPorTaxaFrete(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome-cozinha-e-taxa-frete")
	public List<Restaurante> buscarPorNomeDeCozinhaContendoETaxaFrete(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal){
		return service.buscarPorNomeDeCozinhaContendoETaxaFrete(nomeCozinha, taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public Integer contarPorCozinhas(Long cozinhaId){
		return service.contarPorCozinhas(cozinhaId);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nomeCozinha) {
		return repositoryRestaurante.findComFreteGratis(nomeCozinha);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurntePrimeiro(){
		return repositoryRestaurante.buscarPrimeiro();
	}
	
	//
	
	@GetMapping("/cozinhas/primeira")
	public Optional<Cozinha> cozinhaPrimeira(){
		return repositoryCozinha.buscarPrimeiro();
	}
}
