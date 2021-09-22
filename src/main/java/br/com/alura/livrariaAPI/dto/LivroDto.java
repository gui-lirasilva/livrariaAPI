package br.com.alura.livrariaAPI.dto;

import br.com.alura.livrariaAPI.modelo.Autor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {
	
	private String titulo;
	private Autor autor;
	private int numeroDePaginas;
}
