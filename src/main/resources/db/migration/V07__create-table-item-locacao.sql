create table item_locacao(
    id bigint auto_increment,
    dias int not null,
    jogos_plataformas_id bigint,
    locacao_id bigint,
    primary key(id),
    foreign key(jogos_plataformas_id) references jogos_plataformas(id),
    foreign key(locacao_id) references locacao(id)
);