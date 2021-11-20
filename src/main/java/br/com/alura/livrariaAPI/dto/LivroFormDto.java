package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroFormDto {
	
	@NotBlank
	@Size(min = 10, max = 80)
	private String titulo;
	
	@Min(value = 100, message = "{livro.paginas.invalido}")
	@NotNull
	private Integer numeroDePaginas;
	
	@PastOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;
	
	@JsonAlias("autor_id")
	private Long autorId;	
}
