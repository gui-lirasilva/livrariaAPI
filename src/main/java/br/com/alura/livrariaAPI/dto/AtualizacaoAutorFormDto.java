package br.com.alura.livrariaAPI.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoAutorFormDto extends AutorFormDto {
	
	@NotNull
	public Long id;
}
