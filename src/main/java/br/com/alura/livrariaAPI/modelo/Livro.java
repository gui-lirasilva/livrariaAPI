package br.com.alura.livrariaAPI.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String titulo;
	
	@ManyToOne
	private Autor autor;
	
	private Integer numeroDePaginas;
	
	private LocalDate dataLancamento;

	public Livro(String titulo, Autor autor, Integer numeroDePaginas, LocalDate dataLancamento) {
		this.titulo = titulo;
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
		this.dataLancamento = dataLancamento;
	}

	public void atualizarInformacoes(String titulo, Autor autor, Integer numeroDePaginas, LocalDate dataLancamento) {
		this.titulo = titulo;
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
		this.dataLancamento = dataLancamento;
	}
}
