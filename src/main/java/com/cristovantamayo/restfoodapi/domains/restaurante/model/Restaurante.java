package com.cristovantamayo.restfoodapi.domains.restaurante.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.cristovantamayo.restfoodapi.domains.cozinha.model.Cozinha;
import com.cristovantamayo.restfoodapi.domains.formapagamento.model.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "restaurante_forma_pagamento", 
		joinColumns = @JoinColumn(name = "restaurante_id"), 
		inverseJoinColumns = @JoinColumn(name="forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
}
