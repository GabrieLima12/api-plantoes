CREATE TABLE enderecos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero INT,
    complemento VARCHAR(255),
    bairro VARCHAR(150) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    cep VARCHAR(20) NOT NULL
);

ALTER TABLE unidades
ADD endereco_id BIGINT,
ADD FOREIGN KEY (endereco_id) REFERENCES enderecos(id);

ALTER TABLE medicos
ADD endereco_id BIGINT,
ADD FOREIGN KEY (endereco_id) REFERENCES enderecos(id);