package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {
	
	private Long id;
	private String titulo;
	private Integer numeroDePaginas;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;
	private Long autorId;
	
}
