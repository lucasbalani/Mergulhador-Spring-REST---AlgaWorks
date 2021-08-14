create table ocorrencia(
	id bigint not null auto_increment,
    idEntrega bigint not null,
    descricao text not null,
    dataRegistro datetime not null,
    
    primary key (id)
);

alter table ocorrencia add constraint fk_ocorrencia_entrega foreign key (idEntrega) references entrega (id);