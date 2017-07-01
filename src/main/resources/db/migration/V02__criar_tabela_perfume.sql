CREATE TABLE perfume (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    sku VARCHAR(50) NOT NULL,
    nome VARCHAR(80) NOT NULL,
    descricao TEXT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    origem VARCHAR(50) NOT NULL,
    id_familia BIGINT(20) NOT NULL,
    FOREIGN KEY (id_familia) REFERENCES familia_olfativa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;