package br.com.alura.livrariaAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {
	
	private String titulo;
	private AutorDto autor;
	private int numeroDePaginas;
}
