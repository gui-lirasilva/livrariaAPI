package br.com.alura.livrariaAPI.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "autores")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "datanascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "minicurriculo")
	private String miniCurriculo;

	public Autor(String nome, LocalDate dataNascimento, String email, String miniCurriculo) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.miniCurriculo = miniCurriculo;
	}
	
	public void atualizarInformacoes(String nome, LocalDate dataNascimento, String email, String miniCurriculo) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.miniCurriculo = miniCurriculo;
	}
	
}
