CREATE TABLE estado (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

INSERT INTO estado (nome) SELECT DISTINCT nome_estado FROM cidade;

ALTER TABLE cidade add column estado_id bigint NOT NULL;

UPDATE cidade c SET c.estado_id =(SELECT e.id FROM estado e WHERE e.nome = c.nome_estado); 

ALTER TABLE cidade ADD CONSTRAINT fk_cidade_estado
	FOREIGN KEY (estado_id) REFERENCES estado(id);

ALTER TABLE cidade DROP COLUMN nome_estado;

ALTER TABLE cidade CHANGE nome_cidade nome VARCHAR(80) NOT NULL;