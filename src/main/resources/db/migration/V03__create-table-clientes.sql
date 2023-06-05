create table clientes(
    id bigint auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    telefone varchar(100) not null,
    senha varchar(200) not null,
    primary key(id)
);