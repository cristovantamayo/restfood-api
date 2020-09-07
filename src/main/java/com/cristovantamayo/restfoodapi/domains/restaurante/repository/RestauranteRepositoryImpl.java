package com.cristovantamayo.restfoodapi.domains.restaurante.repository;

import static com.cristovantamayo.restfoodapi.domains.restaurante.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.cristovantamayo.restfoodapi.domains.restaurante.repository.spec.RestauranteSpecs.comNomeCozinhaSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository repository;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome))
			predicates.add(builder.like(root.get("cozinha").get("nome"), "%"+nome+"%"));
		
		if(taxaInicial != null)
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
		
		if(taxaFinal != null)
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
		
		criteria.where(predicates.toArray(new Predicate[0]	));
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nomeCozinha) {
		return repository.findAll(comFreteGratis().and(comNomeCozinhaSemelhante(nomeCozinha)));
	}
	
}
