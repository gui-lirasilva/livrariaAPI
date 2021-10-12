package br.com.alura.livrariaAPI.repository;

import br.com.alura.livrariaAPI.dto.RelatorioLivrosAutorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.livrariaAPI.modelo.Livro;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	
    @Query("select new br.com.alura.livrariaAPI.dto.RelatorioLivrosAutorDto("
			+ "a.nome, "
			+ "count(l.autor), "
			+ "count (l.autor) * 1.0 / (select count(l2.autor) from Livro l2) * 1.0) "
			+ "from Livro l "
			+ "inner join Autor a "
			+ "on a.id = l.autor "
			+ "group by a.nome")
	List<RelatorioLivrosAutorDto> relatorioDeLivros();
}
