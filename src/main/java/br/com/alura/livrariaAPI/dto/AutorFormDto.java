package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFormDto {
	
	@NotBlank
	private String nome;
	
	@Past
	private LocalDate dataNascimento;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 5, max = 250)
	private String miniCurriculo;
}
