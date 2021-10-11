package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFormDto {
	
	@NotBlank
	private String nome;
	
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 5, max = 250)
	private String miniCurriculo;
}
