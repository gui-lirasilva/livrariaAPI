package br.com.alura.livrariaAPI.modelo;

import java.time.LocalDate;

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
	private Long id;
	
	private String nome;
	
	private LocalDate dataNascimento;
	
	private String email;
	
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
