CREATE TABLE venda (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao DATETIME NOT NULL,
    valor_frete DECIMAL(10,2),
    valor_desconto DECIMAL(10,2),
    valor_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    observacao VARCHAR(200),
    data_hora_entrega DATETIME,
    id_cliente BIGINT(20) NOT NULL,
    id_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_venda (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    quantidade INTEGER NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    id_perfume BIGINT(20) NOT NULL,
    id_venda BIGINT(20) NOT NULL,
    FOREIGN KEY (id_perfume) REFERENCES perfume(id),
    FOREIGN KEY (id_venda) REFERENCES venda(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;