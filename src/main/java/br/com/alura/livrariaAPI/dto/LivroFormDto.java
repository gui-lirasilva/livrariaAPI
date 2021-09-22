package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.alura.livrariaAPI.modelo.Autor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroFormDto {
	
	@NotBlank
	private String titulo;
	
	@NotNull
	private Autor autor;
	
	@Min(100)
	private int numeroDePaginas;
	
	@PastOrPresent
	private LocalDate dataLancamento;
}
