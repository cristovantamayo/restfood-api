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

INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Dinheiro')
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartão de Débito')
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Cartão de Crédito')

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);