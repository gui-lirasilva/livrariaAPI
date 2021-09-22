package br.com.alura.livrariaAPI.modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Autor {
	
	private Integer id;
	private String nome;
	private LocalDate dataNascimento;
	private String email;
	private String miniCurriculo;
	
}
