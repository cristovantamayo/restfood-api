package com.cristovantamayo.restfoodapi.domains.estado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cristovantamayo.restfoodapi.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("estado")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estado {
	
	@NotNull(groups = Groups.EstadoId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;

}
