CREATE TABLE unidades (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_unidade VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE especialidades (
    id INT NOT NULL AUTO_INCREMENT,
    nome_especialidade VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    crm VARCHAR(6) NOT NULL UNIQUE,
    nome_medico VARCHAR(150) NOT NULL,
    data_cadastro DATE NOT NULL,
    status VARCHAR(10) NOT NULL,
    especialidade_primaria_id INT,
    especialidade_secundaria_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (especialidade_primaria_id) REFERENCES especialidades(id),
    FOREIGN KEY (especialidade_secundaria_id) REFERENCES especialidades(id)
);

CREATE TABLE relacao_medico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    medico_id BIGINT NOT NULL,
    unidade_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (medico_id) REFERENCES medicos(id),
    FOREIGN KEY (unidade_id) REFERENCES unidades(id)
);