package br.com.alura.livrariaAPI.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelatorioLivrosAutorDto {
	
	private String nomeDoAutor;
    private Long quantidadeLivros;
    private BigDecimal percentual;
    
    public RelatorioLivrosAutorDto(String nomeDoAutor, Long quantidadeDeLivros, Double percentual) {
        this.nomeDoAutor = nomeDoAutor;
        this.quantidadeLivros = quantidadeDeLivros;
        this.percentual = new BigDecimal(percentual.toString());
      }
}
