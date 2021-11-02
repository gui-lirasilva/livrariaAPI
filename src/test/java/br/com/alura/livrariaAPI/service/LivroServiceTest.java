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
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.livrariaAPI.dto.LivroDto;
import br.com.alura.livrariaAPI.dto.LivroFormDto;
import br.com.alura.livrariaAPI.repository.AutorRepository;
import br.com.alura.livrariaAPI.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LivroServiceTest {
	
	@Mock
	  private LivroRepository livroRepository;
	  @Mock
	  private AutorRepository autorRepository;

	  @InjectMocks
	  private LivroService livroService;

	 private LivroFormDto livroFormDto = new LivroFormDto(
			 "Titulo do livro", 
			 300, 
			 LocalDate.parse("2020-10-10"), 
			 1l);

	  @Test
	  void deveriaCadastrarLivro() {
	    LivroFormDto livroFormDtoTeste = livroFormDto;
	    LivroDto livroDto = livroService.cadastrar(livroFormDtoTeste);

	    Mockito.verify(livroRepository).save(Mockito.any());

	    assertEquals(livroFormDto.getTitulo(), livroDto.getTitulo());
	    assertEquals(livroFormDto.getDataLancamento(), livroDto.getDataLancamento());
	    assertEquals(livroFormDto.getNumeroDePaginas(), livroDto.getNumeroDePaginas());
	    assertEquals(livroFormDto.getAutorId(), livroDto.getAutorId());
	  }

	  @Test
	  void naoDeveriaCadastrarLivroComAutorInexistente() {
		  LivroFormDto livroFormDtoTeste = livroFormDto;
	    Mockito.when(autorRepository.getById(livroFormDtoTeste.getAutorId())).thenThrow(EntityNotFoundException.class);
	    assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(livroFormDtoTeste));
	  }


}
