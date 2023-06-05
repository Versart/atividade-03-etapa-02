create table jogos_plataformas(
    id bigint auto_increment,
    jogos_id bigint,
    plataformas_id bigint,
    primary key(id),
    foreign key(jogos_id) references jogos(id),
    foreign key(plataformas_id) references plataformas(id)
);