package com.cristovantamayo.restfoodapi.domains.cidade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.cristovantamayo.restfoodapi.core.validation.Groups;
import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;
}
