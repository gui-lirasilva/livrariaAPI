create table autores(
	id bigint not null auto_increment,
	nome varchar (255) not null,
	dataNascimento date not null,
	email varchar (255)  not null,
	miniCurriculo varchar (255) not null,
	primary key (id)
);