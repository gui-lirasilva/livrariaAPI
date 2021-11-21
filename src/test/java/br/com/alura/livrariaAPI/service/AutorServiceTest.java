package br.com.alura.livrariaAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.livrariaAPI.dto.AutorDto;
import br.com.alura.livrariaAPI.dto.AutorFormDto;
import br.com.alura.livrariaAPI.modelo.Autor;
import br.com.alura.livrariaAPI.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AutorServiceTest {

	@Mock
	private AutorRepository autorRepository;
	
	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private AutorService autorService;

	@Test
	void deveriaCadastrarUmAutor() {
		
		AutorFormDto autorFormDto = new AutorFormDto(
				"Arthur Conan Doyle", 
				LocalDate.parse("1859-05-22"), 
				"arthur@gmail.com",
				"Autor das historias de Sherlock Holmes");
		
		Autor autor = new Autor(
				autorFormDto.getNome(), 
				autorFormDto.getDataNascimento(),
				autorFormDto.getEmail(), 
				autorFormDto.getMiniCurriculo());

		Mockito.when(modelMapper.map(autorFormDto, Autor.class)).thenReturn(autor);

	    Mockito.when(modelMapper.map(autor, AutorDto.class))
	      .thenReturn(new AutorDto(autor.getNome(), autor.getDataNascimento(), autor.getEmail(), autor.getMiniCurriculo()));

	    AutorDto autorDto = autorService.cadastrar(autorFormDto);

	    Mockito.verify(autorRepository).save(Mockito.any());

	    assertEquals(autorFormDto.getNome(), autorDto.getNome());
	    assertEquals(autorFormDto.getDataNascimento(), autorDto.getDataNascimento());
	    assertEquals(autorFormDto.getEmail(), autorDto.getEmail());
	    assertEquals(autorFormDto.getMiniCurriculo(), autorDto.getMiniCurriculo());
	}
}
