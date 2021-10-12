package br.com.alura.livrariaAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelatorioLivrosAutorDto {
	
	private String autor;
    private Long quantidadeLivros;
    private Double percentual;
}
