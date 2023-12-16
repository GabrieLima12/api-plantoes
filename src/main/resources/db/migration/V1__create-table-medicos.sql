CREATE TABLE medicos (

    id BIGINT NOT NULL AUTO_INCREMENT,
    crm VARCHAR(6) NOT NULL UNIQUE,
    nome_medico VARCHAR(150) NOT NULL,
    data_cadastro DATE NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    status VARCHAR(10) NOT NULL,
    nome_unidade VARCHAR(150) NOT NULL,

    primary key(id)

);
