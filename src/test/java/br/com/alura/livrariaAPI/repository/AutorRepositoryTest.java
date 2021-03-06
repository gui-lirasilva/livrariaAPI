package br.com.alura.livrariaAPI.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livrariaAPI.modelo.Autor;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class AutorRepositoryTest {

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void deveriaCadastrarUmAutor() {
		Autor autor = new Autor(
				"Arthur Conan Doyle", 
				LocalDate.parse("1859-05-22"), 
				"arthur@gmail.com",
				"Autor das historias de Sherlock Holmes");
		testEntityManager.persist(autor);
		Assertions.assertThat(autorRepository.findAll().contains(autor)).isTrue();
	}

	@Test
	void deveriaListarAutores() {
		Autor autor1 = new Autor(
				"Nome do autor 1", 
				LocalDate.parse("1869-05-22"), 
				"autor1@gmail.com",
				"Autor dos livros de ficcao");
		testEntityManager.persist(autor1);

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

		List<Autor> autores = autorRepository.findAll();
		Assertions
			.assertThat(autores)
			.hasSize(3)
			.extracting(Autor::getNome, Autor::getDataNascimento, Autor::getEmail, Autor::getMiniCurriculo)
			.containsExactlyInAnyOrder(
			Assertions.tuple(
					"Nome do autor 1", 
					LocalDate.parse("1869-05-22"), 
					"autor1@gmail.com",
					"Autor dos livros de ficcao"),
			Assertions.tuple(
					"Nome do autor 2", 
					LocalDate.parse("1879-06-23"), 
					"autor2@gmail.com",
					"Autor dos livros de fantasia"),
			Assertions.tuple(
					"Nome do autor 3", 
					LocalDate.parse("1889-07-24"), 
					"autor3@gmail.com",
					"Autor dos livros de fabulas"));

	}
	
	@Test
	void deveriaAtualizarUmAutor() {
		Autor autor = new Autor(
				"Nome de um autor qualquer", 
				LocalDate.parse("1969-11-15"), 
				"autorQualquer@gmail.com",
				"Autor de varios livros");
		testEntityManager.persist(autor);

		autor.atualizarInformacoes(
				"Bob Kane", 
				LocalDate.parse("1915-10-24"), 
				"bobMorcego@gmail.com",
				"Co-autor e criador do Batman nos quadrinhos");
		testEntityManager.merge(autor);

		List<Autor> autores = autorRepository.findAll();
		Assertions.assertThat(autores).hasSize(1)
				.extracting(Autor::getNome, Autor::getDataNascimento, Autor::getEmail, Autor::getMiniCurriculo)
				.contains(Assertions.tuple(
						"Bob Kane", 
						LocalDate.parse("1915-10-24"), 
						"bobMorcego@gmail.com",
						"Co-autor e criador do Batman nos quadrinhos"));
	}
	
	@Test
	  void deveriaRemoverUmAutor() {
	    Autor autor = new Autor(
	    		"Nome de um autor qualquer", 
				LocalDate.parse("1969-11-15"), 
				"autorQualquer@gmail.com",
				"Autor de varios livros");
	    testEntityManager.persistAndGetId(autor);
	    testEntityManager.remove(autor);
	    Assertions.assertThat(autorRepository.findAll()).isEmpty();
	  }
	
	 @Test
	  void deveriaEncontrarUmAutorPorId() {
	    Autor autor = new Autor(
	    		"Isaac Asimov", 
	    		LocalDate.parse("1920-01-02"), 
	    		"asimov@gmail.com", 
	    		"Um dos mestres da ficcao cientifica");
	    Long autorId = testEntityManager.persistAndGetId(autor, Long.class);

	    Assertions.assertThat(autorRepository.getById(autorId)).isNotNull();
	  }

}
