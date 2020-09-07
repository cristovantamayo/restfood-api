package com.cristovantamayo.restfoodapi.domains.restaurante.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

public class RestauranteSpecs {

	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) ->
			builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeCozinhaSemelhante(String nomeCozinha) {
		return (root, query, builder) ->
			builder.like(root.get("cozinha").get("nome"), "%"+nomeCozinha+"%");
	}
	
}
