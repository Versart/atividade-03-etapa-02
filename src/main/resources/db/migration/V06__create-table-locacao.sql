create table locacao(
    id bigint auto_increment,
    data_locacao date not null,
    id_cliente bigint,
    primary key(id),
    foreign key(id_cliente) references clientes(id)
);