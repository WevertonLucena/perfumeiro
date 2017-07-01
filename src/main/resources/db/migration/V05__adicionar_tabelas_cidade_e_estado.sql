CREATE TABLE estado (
    id BIGINT(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sigla VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    id_estado BIGINT(20) NOT NULL,
    FOREIGN KEY (id_estado) REFERENCES estado(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO estado (id, nome, sigla) VALUES (1,'Acre', 'AC');
INSERT INTO estado (id, nome, sigla) VALUES (2,'Bahia', 'BA');
INSERT INTO estado (id, nome, sigla) VALUES (3,'Goiás', 'GO');
INSERT INTO estado (id, nome, sigla) VALUES (4,'Minas Gerais', 'MG');
INSERT INTO estado (id, nome, sigla) VALUES (5,'Paraná', 'PR');
INSERT INTO estado (id, nome, sigla) VALUES (6,'Santa Catarina', 'SC');
INSERT INTO estado (id, nome, sigla) VALUES (7,'São Paulo', 'SP');



INSERT INTO cidade (nome, id_estado) VALUES ('Rio Branco', 1);
INSERT INTO cidade (nome, id_estado) VALUES ('Cruzeiro do Sul', 1);
INSERT INTO cidade (nome, id_estado) VALUES ('Salvador', 2);
INSERT INTO cidade (nome, id_estado) VALUES ('Porto Seguro', 2);
INSERT INTO cidade (nome, id_estado) VALUES ('Santana', 2);
INSERT INTO cidade (nome, id_estado) VALUES ('Goiânia', 3);
INSERT INTO cidade (nome, id_estado) VALUES ('Itumbiara', 3);
INSERT INTO cidade (nome, id_estado) VALUES ('Novo Brasil', 3);
INSERT INTO cidade (nome, id_estado) VALUES ('Belo Horizonte', 4);
INSERT INTO cidade (nome, id_estado) VALUES ('Uberlândia', 4);
INSERT INTO cidade (nome, id_estado) VALUES ('Montes Claros', 4);
INSERT INTO cidade (nome, id_estado) VALUES ('Florianópolis', 5);
INSERT INTO cidade (nome, id_estado) VALUES ('Criciúma', 5);
INSERT INTO cidade (nome, id_estado) VALUES ('Camboriú', 5);
INSERT INTO cidade (nome, id_estado) VALUES ('Lages', 5);
INSERT INTO cidade (nome, id_estado) VALUES ('São Paulo', 6);
INSERT INTO cidade (nome, id_estado) VALUES ('Ribeirão Preto', 6);
INSERT INTO cidade (nome, id_estado) VALUES ('Campinas', 6);
INSERT INTO cidade (nome, id_estado) VALUES ('Santos', 6);