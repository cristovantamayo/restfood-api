create table pedido (
	id bigint not null auto_increment, 
	subtotal decimal(19,2) not null, 
	taxa_frete decimal(19,2) not null, 
	valor_total decimal(19,2) not null,
	
	restaurante_id bigint not null, 
	usuario_cliente_id bigint not null, 
	forma_pagamento_id bigint not null,
	 
	endereco_cidade_id bigint not null, 
	endereco_cep varchar(9) not null, 
	endereco_logradouro varchar(120) not null, 
	endereco_numero varchar(10) not null,
	endereco_complemento varchar(100),
	endereco_bairro varchar(60) not null, 
	 
	status varchar(10) not null, 
	created_at datetime not null, 
	confirmed_at datetime(6), 
	canceled_at datetime(6), 
	delivered_ay datetime(6),
	 
	primary key (id),
	
	constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
    constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table item_pedido (
	id bigint not null auto_increment, 
	quantidade smallint(6) not null, 
	preco_unitario decimal(19,2) not null, 
	preco_total decimal(19,2) not null, 
	observacoes varchar(255) null, 
	pedido_id bigint not null,
	produto_id bigint not null,
	
	primary key (id),
	unique key uk_item_pedido_produto (pedido_id, produto_id),
	
	constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
	constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;