DROP TABLE IF EXISTS peças_sinistros;
DROP TABLE IF EXISTS peças;
DROP TABLE IF EXISTS sinistros;
DROP TABLE IF EXISTS seguradoras;

CREATE TABLE seguradoras (
    nome VARCHAR(50) PRIMARY KEY,
    cidade VARCHAR(50),
    cobertura_percentual INT,
    possui_atendimento_24h BOOLEAN,
    forma_pagamento_preferencial VARCHAR(20)
);

CREATE TABLE sinistros (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    segurado VARCHAR(80) NOT NULL,
    telefone VARCHAR(20),
    cidade VARCHAR(50),
    grau_monta VARCHAR(20),
    perda_total BOOLEAN
);

CREATE TABLE peças (
    codigo INT PRIMARY KEY,
    nome VARCHAR(50),
    marca VARCHAR(20),
    preco DECIMAL(10,2),
    mao_obra_propria BOOLEAN,
    dias_garantia INT,
    cor VARCHAR(20)
);

CREATE TABLE peças_sinistros (
    peça_codigo INT NOT NULL,
    sinistro_id INT NOT NULL,
    PRIMARY KEY (peça_codigo, sinistro_id),
    FOREIGN KEY (peça_codigo) REFERENCES peças(codigo) ON DELETE CASCADE,
    FOREIGN KEY (sinistro_id) REFERENCES sinistros(id) ON DELETE CASCADE
);
