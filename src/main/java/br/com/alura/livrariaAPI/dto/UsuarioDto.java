package br.com.alura.livrariaAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

	private Long id;
	private String nome;
	private String login;
}
