DROP DATABASE IF EXISTS anotacao_db;

CREATE DATABASE anotacao_db;

\c anotacao_db;

CREATE TABLE anotacao (
    id serial primary key,
    titulo text, 
    texto text,
    cor text, 
    data_hora timestamp default current_timestamp,
    foto bytea
);

INSERT INTO anotacao (titulo, texto) VALUES
('ANOTACAO1', 'TEXTO1'),
('ANOTACAO2', 'TEXTO2');

CREATE VIEW visao AS SELECT * FROM anotacao;


