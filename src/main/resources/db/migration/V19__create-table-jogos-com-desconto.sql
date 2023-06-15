create table jogos_com_desconto(
    id bigint auto_increment,
    porcentagem_desconto double,
    jogos_plataformas_id bigint,
    primary key(id),
    foreign key(jogos_plataformas_id) references jogos_plataformas(id)
);

