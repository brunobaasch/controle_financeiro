CREATE TYPE tipo_transacao AS ENUM('receita', 'despesa');

CREATE TABLE contas (
    id_conta    INTEGER PRIMARY KEY,
    nome        VARCHAR(30) NOT NULL,
    cpf         VARCHAR(11) NOT NULL,
    email       VARCHAR(50) NOT NULL,
	saldo		DECIMAL(10,2) DEFAULT 0,
	tipo		tipo_transacao NOT NULL
);

CREATE TABLE categorias (
    id_categoria    INTEGER PRIMARY KEY,
    nome            VARCHAR(50) NOT NULL
);

CREATE TABLE transacoes (
    id_transacao        INTEGER PRIMARY KEY,
    valor               DECIMAL(10,2) NOT NULL,
    data                TIMESTAMP NOT NULL,
    id_conta            INTEGER NOT NULL,
	tipo				tipo_transacao NOT NULL,
    FOREIGN KEY (id_conta) REFERENCES contas(id_conta)
);

CREATE TABLE categoria_transacoes (
    id_categoria INTEGER NOT NULL,
    id_transacao INTEGER NOT NULL,

    PRIMARY KEY (id_categoria, id_transacao),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_transacao) REFERENCES transacoes(id_transacao)
);