package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {

	private Long id;
	private String titulo;
	private Integer numeroDePaginas;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;
	private Long autorId;

	public LivroDto(String titulo, Integer numeroDePaginas, LocalDate dataLancamento, Long autorId) {
		this.titulo = titulo;
		this.numeroDePaginas = numeroDePaginas;
		this.dataLancamento = dataLancamento;
		this.autorId = autorId;
	}
}
