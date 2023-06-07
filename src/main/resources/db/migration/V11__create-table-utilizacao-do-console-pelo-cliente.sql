create table utilizacao_do_console_pelo_cliente(
    id bigint auto_increment,
    inicio datetime not null,
    fim datetime not null,
    console_id bigint,
    cliente_id bigint,
    primary key(id),
    foreign key(console_id) references console(id),
    foreign key(cliente_id) references clientes(id)
);