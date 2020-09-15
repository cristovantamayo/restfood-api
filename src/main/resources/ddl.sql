create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table item_pedido (id bigint not null auto_increment, observacoes varchar(255), preco_total decimal(19,2) not null, preco_unitario decimal(19,2) not null, quantidade integer not null, pedido_id bigint, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table pedido (id bigint not null auto_increment, canceled_at datetime(6), confirmed_at datetime(6), created_at datetime not null, delivered_ay datetime(6), endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), status integer, subtotal decimal(19,2) not null, taxa_frete decimal(19,2) not null, valor_total decimal(19,2) not null, endereco_cidade_id bigint, restaurante_id bigint, usuario_id bigint, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table pedido_formas_pagamento (pedido_id bigint not null, formas_pagamento_id bigint not null) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table pedido_itens (pedido_id bigint not null, itens_id bigint not null) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table produto (id bigint not null auto_increment, ativo tinyint(1) default 1 not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table restaurante (id bigint not null auto_increment, created_at datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, updated_at datetime not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table usuario (id bigint not null auto_increment, created_at datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, updated_at datetime not null, primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) ENGINE=InnoDB DEFAULT CHARSET=utf8
alter table pedido_formas_pagamento add constraint UK_kikj6co1k5snwy0807l92oye4 unique (formas_pagamento_id)
alter table pedido_itens add constraint UK_mu8rg6jm44j40igjdcg5jvh5k unique (itens_id)
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table pedido add constraint FK6uxomgomm93vg965o8brugt00 foreign key (usuario_id) references usuario (id)
alter table pedido_formas_pagamento add constraint FKjm7ibnftuw8pa7fes49x6alvk foreign key (formas_pagamento_id) references forma_pagamento (id)
alter table pedido_formas_pagamento add constraint FKbe1xl2tr3b03i8kxuiee4oj1v foreign key (pedido_id) references pedido (id)
alter table pedido_itens add constraint FKpgxyyixiy6xcc75yujqnb8sg foreign key (itens_id) references item_pedido (id)
alter table pedido_itens add constraint FKb5t2ga17uxph7bp32w97ssu4t foreign key (pedido_id) references pedido (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
