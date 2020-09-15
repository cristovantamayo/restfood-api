package com.cristovantamayo.restfoodapi.domains.pedido.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cristovantamayo.restfoodapi.domains.formapagamento.model.FormaPagamento;
import com.cristovantamayo.restfoodapi.domains.restaurante.model.Endereco;
import com.cristovantamayo.restfoodapi.domains.restaurante.model.Restaurante;
import com.cristovantamayo.restfoodapi.domains.usuario.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private BigDecimal subtotal;
	
	@Column(nullable=false)
	private BigDecimal taxaFrete;
	
	@Column(nullable=false)
	private BigDecimal valorTotal;
	
	@Embedded
	@Column(nullable=false)
	private Endereco enderecoEntrega;
	
	@Column(nullable=false)
	private StatusPedido status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	private LocalDateTime confirmedAt;
	private LocalDateTime canceledAt;
	private LocalDateTime deliveredAy;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable=false)
	private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	
}
