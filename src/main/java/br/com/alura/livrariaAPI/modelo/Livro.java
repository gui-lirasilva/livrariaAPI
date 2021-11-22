package br.com.alura.livrariaAPI.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Column(name = "id")
    private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	
	@Column(name = "numerodepaginas")
	private Integer numeroDePaginas;
	
	@Column(name = "datalancamento")
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
