package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorDetalhadoDto extends AutorDto {
	
	private LocalDate DataNascimento;
	public Long id;
}
