create table forma_pagamento (
	id bigint not null auto_increment,
	descricao varchar(50) not null, 
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table grupo (
	id bigint not null auto_increment,
	nome varchar(50) not null, 
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table grupo_permissao (
	grupo_id bigint not null, 
	permissao_id bigint not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table permissao (
	id bigint not null auto_increment, 
	descricao varchar(255) not null, 
	nome varchar(100) not null, 
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table produto (
	id bigint not null auto_increment, 
	ativo tinyint(1) default 1 not null, 
	descricao varchar(255) not null, 
	nome varchar(50) not null, 
	preco decimal(19,2) not null, 
	restaurante_id bigint not null, 
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table restaurante (
	id bigint not null auto_increment, 
	created_at datetime not null, 
	endereco_bairro varchar(40), 
	endereco_cep varchar(100), 
	endereco_complemento varchar(30), 
	endereco_logradouro varchar(100), 
	endereco_numero varchar(10), 
	nome varchar(80) not null, 
	taxa_frete decimal(19,2) not null, 
	updated_at datetime not null, 
	cozinha_id bigint not null, 
	endereco_cidade_id bigint, 
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table restaurante_forma_pagamento (
	restaurante_id bigint not null, 
	forma_pagamento_id bigint not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table usuario (
	id bigint not null auto_increment, 
	created_at datetime not null, 
	email varchar(180) not null, 
	nome varchar(80) not null, 
	senha varchar(255) not null, 
	updated_at datetime not null, 
	primary key (	id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table usuario_grupo (
	usuario_id bigint not null, 
	grupo_id bigint not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table grupo_permissao add constraint FK_grupo_permissao foreign key (permissao_id) references permissao (id);
alter table grupo_permissao add constraint FK_permissao_grupo foreign key (grupo_id) references grupo (id);
alter table produto add constraint FK_produto_restaurante foreign key (restaurante_id) references restaurante (id);
alter table restaurante add constraint FK_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);
alter table restaurante add constraint FK_restaurante_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table restaurante_forma_pagamento add constraint FK_restaurante_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table restaurante_forma_pagamento add constraint FK_forma_pagamento_restaurabte foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupo add constraint FK_usuario_grupo foreign key (grupo_id) references grupo (id);
alter table usuario_grupo add constraint FK_grupo_usuario foreign key (usuario_id) references usuario (id);




