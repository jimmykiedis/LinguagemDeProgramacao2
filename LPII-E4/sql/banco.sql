DROP TABLE IF EXISTS peças_sinistros;
DROP TABLE IF EXISTS `Orçamentos`;
DROP TABLE IF EXISTS peça_mecânica;
DROP TABLE IF EXISTS peça_acessórios;
DROP TABLE IF EXISTS peças;
DROP TABLE IF EXISTS sinistros;
DROP TABLE IF EXISTS seguradoras;

CREATE TABLE seguradoras (
    nome VARCHAR(50) PRIMARY KEY,
    cidade VARCHAR(50),
    cobertura_percentual INT,
    possui_atendimento_24h BOOLEAN,
    forma_pagamento_preferencial INT
);

CREATE TABLE sinistros (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    segurado VARCHAR(80) NOT NULL,
    telefone VARCHAR(20),
    cidade VARCHAR(50),
    grau_monta INT,
    perda_total BOOLEAN
);

CREATE TABLE peças (
    codigo INT PRIMARY KEY,
    nome VARCHAR(50),
    marca INT,
    preço DECIMAL(10,2),
    sob_encomenda BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE peças_sinistros (
    peça_codigo INT NOT NULL,
    sinistro_id INT NOT NULL,
    PRIMARY KEY (peça_codigo, sinistro_id),
    FOREIGN KEY (peça_codigo) REFERENCES peças(codigo) ON DELETE CASCADE,
    FOREIGN KEY (sinistro_id) REFERENCES sinistros(id) ON DELETE CASCADE
);

CREATE TABLE peça_mecânica (
    categoria_mecânica INT NOT NULL,
    prazo_garantia INT,
    mecânico_próprio BOOLEAN NOT NULL DEFAULT FALSE,
    codigoId INT NOT NULL,
    FOREIGN KEY (codigoId) REFERENCES peças (codigo)
);

CREATE TABLE peça_acessórios (
    categoria_acessorio INT NOT NULL,
    cor VARCHAR(20),
    funileiro_próprio BOOLEAN NOT NULL DEFAULT FALSE,
    codigoId INT NOT NULL,
    FOREIGN KEY (codigoId) REFERENCES peças (codigo)
);

CREATE TABLE Orçamentos (
    sequencial INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    sinistro INT NOT NULL,
    seguradora VARCHAR(50) NOT NULL,
    status INT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    possui_danos_ocultos BOOLEAN NOT NULL DEFAULT FALSE,
    UNIQUE KEY uk_orcamento_sinistro_seguradora (sinistro, seguradora),
    FOREIGN KEY (sinistro) REFERENCES sinistros(id),
    FOREIGN KEY (seguradora) REFERENCES seguradoras(nome)
);
