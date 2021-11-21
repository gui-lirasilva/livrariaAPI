package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorDto {

	private Long id;
	private String nome;
	private String email;
	private String miniCurriculo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	public AutorDto(String nome, LocalDate dataDeNascimento, String email, String miniCurriculo) {
		this.nome = nome;
		this.email = email;
		this.miniCurriculo = miniCurriculo;
		this.dataNascimento = dataDeNascimento;
	}
}
