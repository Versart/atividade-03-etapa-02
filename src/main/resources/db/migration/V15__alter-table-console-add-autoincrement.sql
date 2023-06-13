alter table console modify column id bigint auto_increment;
alter table utilizacao_do_console_pelo_cliente add foreign key(console_id) references console(id);