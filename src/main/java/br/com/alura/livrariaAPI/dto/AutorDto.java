package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDto {
	
	private Long id;
	private String nome;
	private String email;
	private String miniCurriculo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
}
