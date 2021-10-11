package br.com.alura.livrariaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.livrariaAPI.modelo.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	
}
