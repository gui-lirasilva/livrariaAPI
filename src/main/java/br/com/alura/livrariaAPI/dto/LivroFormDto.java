package br.com.alura.livrariaAPI.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroFormDto {
	
	@NotBlank
	@Size(min = 10, max = 50, message = "Titulo deve conter entre 10 e 50 caracteres")
	private String titulo;
	
	@Min(100)
	@NotNull
	private Integer numeroDePaginas;
	
	@PastOrPresent(message = "A data deve estar no presente ou passado")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;
	
	@JsonAlias("autor_id")
	private Long autorId;
}
