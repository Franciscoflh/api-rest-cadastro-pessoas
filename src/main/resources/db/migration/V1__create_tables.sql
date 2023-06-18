CREATE TABLE IF NOT EXISTS pessoa (
                                      id SERIAL PRIMARY KEY,
                                      nome VARCHAR(255) NOT NULL,
                                      cpf VARCHAR(11) NOT NULL,
                                      data_nascimento DATE NOT NULL,
                                      contato_id BIGINT REFERENCES contato(id)
);

CREATE TABLE IF NOT EXISTS contato (
                                       id SERIAL PRIMARY KEY,
                                       nome VARCHAR(255) NOT NULL,
                                       telefone VARCHAR(20) NOT NULL,
                                       email VARCHAR(255) NOT NULL,
                                       pessoa_id BIGINT REFERENCES pessoa(id) ON DELETE CASCADE
);