create table consoles_com_desconto(
    id bigint auto_increment,
    porcentagem_desconto double,
    console_id bigint,
    primary key(id),
    foreign key(console_id) references console(id)
);