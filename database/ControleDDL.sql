CREATE TABLE contas (
    id_conta    INTEGER PRIMARY KEY,
    nome        VARCHAR(30) NOT NULL,
    cpf         VARCHAR(11) NOT NULL,
    email       VARCHAR(50) NOT NULL
);

CREATE TABLE tipo_transacoes (
     id_tipo_transacao  INTEGER PRIMARY KEY,
     tipo               VARCHAR(20) NOT NULL
);

CREATE TABLE categoria (
    id_categoria    INTEGER PRIMARY KEY,
    nome            VARCHAR(50) NOT NULL
);

CREATE TABLE transacoes (
    id_transacao        INTEGER PRIMARY KEY,
    valor               DECIMAL(10,2) NOT NULL,
    data                TIMESTAMP NOT NULL,
    id_conta            INTEGER NOT NULL,
    id_tipo_transacao   INTEGER NOT NULL,

    FOREIGN KEY (id_conta) REFERENCES contas(id_conta),
    FOREIGN KEY (id_tipo_transacao) REFERENCES tipo_transacoes(id_tipo_transacao)
);

CREATE TABLE categoria_transacoes (
    id_categoria INTEGER NOT NULL,
    id_transacao INTEGER NOT NULL,

    PRIMARY KEY (id_categoria, id_transacao),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_transacao) REFERENCES transacoes(id_transacao)
);