create table consoles_jogos(
    console_id bigint,
    acessorio_id bigint,
    foreign key(console_id) references console(id),
    foreign key(acessorio_id) references acessorios(id),
    primary key(console_id,acessorio_id)
);