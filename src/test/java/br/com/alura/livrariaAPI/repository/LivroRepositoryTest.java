package br.com.alura.livrariaAPI.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livrariaAPI.dto.RelatorioLivrosAutorDto;
import br.com.alura.livrariaAPI.modelo.Autor;
import br.com.alura.livrariaAPI.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	Autor autor = new Autor(
			"Nome de um autor qualquer", 
			LocalDate.parse("1969-11-15"), 
			"autorQualquer@gmail.com",
			"Autor de varios livros");
	
	@Test
	void deveriaCadastrarUmLivro() {
		Autor autorTeste = autor;
		testEntityManager.persist(autorTeste);
		
		Livro livro = new Livro(
				"Livro teste", 
				autorTeste, 
				300, 
				LocalDate.now());
		
		testEntityManager.persist(livro);

		Assertions.assertThat(livroRepository.findAll().contains(livro)).isTrue();
	}
	
	@Test
	void deveriaListarLivros() {
		Autor autorTeste = autor;
		testEntityManager.persist(autorTeste);

		Livro livro1 = new Livro(
				"Livro 1", 
				autorTeste, 
				300, 
				LocalDate.parse("2020-10-01"));
		testEntityManager.persist(livro1);
		
		Livro livro2 = new Livro(
				"Livro 2", 
				autorTeste, 
				400, 
				LocalDate.parse("2020-11-02"));
		testEntityManager.persist(livro2);
		
		Livro livro3 = new Livro(
				"Livro 3", 
				autorTeste, 
				500, 
				LocalDate.parse("2020-12-03"));
		testEntityManager.persist(livro3);

		List<Livro> livros = livroRepository.findAll();
		Assertions.assertThat(livros).hasSize(3)
				.extracting(Livro::getTitulo, Livro::getAutor, Livro::getNumeroDePaginas, Livro::getDataLancamento)
				.containsExactlyInAnyOrder(
						Assertions.tuple(
								"Livro 1", 
								autorTeste, 
								300, 
								LocalDate.parse("2020-10-01")),
						Assertions.tuple(
								"Livro 2", 
								autorTeste, 
								400, 
								LocalDate.parse("2020-11-02")),
						Assertions.tuple(
								"Livro 3", 
								autorTeste, 
								500, 
								LocalDate.parse("2020-12-03")));
	}
	
	@Test
	void deveriaRetornarRelatorioLivrosPorAutor() {
		Autor autor1 = new Autor(
				"Nome do autor 1", 
				LocalDate.parse("1869-05-22"), 
				"autor1@gmail.com",
				"Autor dos livros de ficcao");
		testEntityManager.persist(autor);
		
		Autor autor2 = new Autor(
				"Nome do autor 2", 
				LocalDate.parse("1879-06-23"), 
				"autor2@gmail.com",
				"Autor dos livros de fantasia");
		testEntityManager.persist(autor2);
		
		Autor autor3 = new Autor(
				"Nome do autor 3", 
				LocalDate.parse("1889-07-24"), 
				"autor3@gmail.com",
				"Autor dos livros de fabulas");
		testEntityManager.persist(autor3);

		Livro livro1 = new Livro(
				"Livro 1", 
				autor1, 
				300, 
				LocalDate.parse("2020-10-01"));
		testEntityManager.persist(livro1);
		
		Livro livro2 = new Livro(
				"Livro 2", 
				autor1, 
				400, 
				LocalDate.parse("2020-10-01"));
		testEntityManager.persist(livro2);
		
		Livro livro3 = new Livro(
				"Livro 3", 
				autor2, 
				500, 
				LocalDate.parse("2020-10-01"));
		testEntityManager.persist(livro3);
		
		Livro livro4 = new Livro(
				"Livro 4", 
				autor3, 
				500, 
				LocalDate.parse("2020-10-01"));
		testEntityManager.persist(livro4);

		List<RelatorioLivrosAutorDto> relatorioLivrosAutorDto = livroRepository.relatorioDeLivros();
		Assertions.assertThat(relatorioLivrosAutorDto).hasSize(3)
				.extracting(
						RelatorioLivrosAutorDto::getNomeDoAutor, 
						RelatorioLivrosAutorDto::getQuantidadeLivros,
						RelatorioLivrosAutorDto::getPercentual)
				.containsExactlyInAnyOrder(
						Assertions.tuple("Autor 01", 2L, new BigDecimal("0.005")),
						Assertions.tuple("Autor 02", 1L, new BigDecimal("0.0025")),
						Assertions.tuple("Autor 03", 1L, new BigDecimal("0.0025")));
	}// Esse teste está dando erro pois o autor_id está indo nulo
	
	 @Test
	  void deveriaAtualizarUmLivro() {
	    Autor autorQualquer = autor;
	    testEntityManager.persist(autorQualquer);

		Livro livroQualquer = new Livro(
				"Livro qualquer", 
				autorQualquer, 
				500, 
				LocalDate.parse("2020-10-01"));
		testEntityManager.persist(livroQualquer);

		livroQualquer.atualizarInformacoes(
				"Outro titulo", 
				autorQualquer, 
				400, 
				LocalDate.parse("2005-05-05"));
	    testEntityManager.merge(livroQualquer);

	    List<Livro> livros = livroRepository.findAll();
	    Assertions.assertThat(livros).hasSize(1).extracting(
	    		Livro::getTitulo,
	    		Livro::getAutor,
	    		Livro::getNumeroDePaginas,
	    		Livro::getDataLancamento)
	      .containsExactlyInAnyOrder(
	    		  Assertions.tuple(
	    			"Outro titulo",
	    			autorQualquer,
					400, 
					LocalDate.parse("2005-05-05")));

	  }
	 
	 @Test
	  void deveriaRemoverUmLivro() {
	    Autor autorQualquer = autor;
	    testEntityManager.persist(autorQualquer);

	    Livro livroQualquer = new Livro(
				"Livro qualquer", 
				autorQualquer, 
				500, 
				LocalDate.parse("2020-10-01"));
	    
		testEntityManager.persist(livroQualquer);
		
		 testEntityManager.remove(livroQualquer);

	    Assertions.assertThat(livroRepository.findAll()).isEmpty();
	  }
	 
	 @Test
	  void deveriaEncontrarUmLivroPorId() {
	    Autor autorQualquer = autor;
	    testEntityManager.persist(autorQualquer);

	    Livro livroQualquer = new Livro(
				"Livro qualquer", 
				autorQualquer, 
				500, 
				LocalDate.parse("2020-10-01"));
	    
	    Long livroId = testEntityManager.persistAndGetId(livroQualquer, Long.class);

	    Assertions.assertThat(livroRepository.getById(livroId)).isNotNull();
	  }
}
