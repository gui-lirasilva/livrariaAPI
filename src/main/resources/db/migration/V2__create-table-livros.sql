create table livros(
	id bigint not null auto_increment,
	titulo varchar (255) not null,
	numeroDePaginas int  not null,
	dataLancamento date not null,
	primary key (id)
);
