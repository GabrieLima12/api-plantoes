CREATE TABLE unidades (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_unidade VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    crm VARCHAR(6) NOT NULL UNIQUE,
    nome_medico VARCHAR(150) NOT NULL,
    data_cadastro DATE NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    status VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medico_unidade (
    id BIGINT NOT NULL AUTO_INCREMENT,
    medico_id BIGINT NOT NULL,
    unidade_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_medico
    FOREIGN KEY (medico_id)
    REFERENCES medicos(id),
    CONSTRAINT fk_unidade
    FOREIGN KEY (unidade_id)
    REFERENCES unidades(id)
);