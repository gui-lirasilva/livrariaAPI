package br.com.alura.livrariaAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.livrariaAPI.dto.LivroDto;
import br.com.alura.livrariaAPI.dto.LivroFormDto;
import br.com.alura.livrariaAPI.modelo.Autor;
import br.com.alura.livrariaAPI.modelo.Livro;
import br.com.alura.livrariaAPI.repository.AutorRepository;
import br.com.alura.livrariaAPI.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LivroServiceTest {

	@Mock
	private LivroRepository livroRepository;
	@Mock
	private AutorRepository autorRepository;
	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private LivroService livroService;

	@Test
	void deveriaCadastrarLivro() {

		Autor autor = new Autor(
				1l, 
				"Arthur Conan Doyle", 
				LocalDate.parse("1859-05-22"), 
				"arthur@gmail.com",
				"Autor das historias de Sherlock Holmes");

		LivroFormDto livroFormDto = new LivroFormDto(
				"Titulo do livro", 
				300, 
				LocalDate.now(), 
				autor.getId());

		Livro livro = new Livro(
				2l, 
				livroFormDto.getTitulo(), 
				autor, 
				livroFormDto.getNumeroDePaginas(),
				livroFormDto.getDataLancamento());

		Mockito.when(modelMapper.map(livroFormDto, Livro.class)).thenReturn(livro);

		Mockito.when(modelMapper.map(livro, LivroDto.class)).thenReturn(
				new LivroDto(
						livro.getTitulo(), 
						livro.getNumeroDePaginas(), 
						livro.getDataLancamento(), 
						autor.getId()));
		
		LivroDto livroDto = livroService.cadastrar(livroFormDto);

	    Mockito.verify(livroRepository).save(Mockito.any());

	    assertEquals(livroFormDto.getTitulo(), livroDto.getTitulo());
	    assertEquals(livroFormDto.getDataLancamento(), livroDto.getDataLancamento());
	    assertEquals(livroFormDto.getNumeroDePaginas(), livroDto.getNumeroDePaginas());
	    assertEquals(livroFormDto.getAutorId(), livroDto.getAutorId());
	}

	@Test
	void naoDeveriaCadastrarLivroComAutorInexistente() {
		LivroFormDto livroFormDto = new LivroFormDto(
				"Titulo do livro", 
				300, 
				LocalDate.now(), 
				99l);
		
		Mockito.when(
				autorRepository
				.getById(livroFormDto.getAutorId()))
		.thenThrow(EntityNotFoundException.class);
		
		assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(livroFormDto));
	}

}
