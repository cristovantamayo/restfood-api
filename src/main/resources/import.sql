INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa')
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana')

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Cozinha Owika Tailândia', 8, 1)
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Muay Food', 10, 1)
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Namaste Food', 10, 2)

INSERT INTO estado (id, nome) VALUES (1, 'São Paulo')
INSERT INTO estado (id, nome) VALUES (2, 'Santa Catarina')

INSERT INTO cidade (nome, estado_id) VALUES ('Taubaté', 1)
INSERT INTO cidade (nome, estado_id) VALUES ('São José dos Campos', 1)
INSERT INTO cidade (nome, estado_id) VALUES ('Florianopolis', 2)
INSERT INTO cidade (nome, estado_id) VALUES ('Bombinhas', 2)