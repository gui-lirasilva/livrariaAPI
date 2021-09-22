package br.com.alura.livrariaAPI.modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
	
	private String titulo;
	private Autor autor;
	private int numeroDePaginas;
	private LocalDate dataLancamento;
}
