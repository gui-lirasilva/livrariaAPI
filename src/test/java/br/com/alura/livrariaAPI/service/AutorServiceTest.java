package br.com.alura.livrariaAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livrariaAPI.dto.AutorDto;
import br.com.alura.livrariaAPI.dto.AutorFormDto;
import br.com.alura.livrariaAPI.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
	private AutorRepository autorRepository;

	@InjectMocks
	private AutorService autorService;

	@Test
	void deveriaCadastrarUmAutor() {
		AutorFormDto autorFormDto = new AutorFormDto(
				"Arthur Conan Doyle", 
				LocalDate.parse("1859-05-22"), 
				"arthur@gmail.com",
				"Autor das historias de Sherlock Holmes");
		AutorDto autorDto = autorService.cadastrar(autorFormDto);

		Mockito.verify(autorRepository).save(Mockito.any());

		assertEquals(autorFormDto.getNome(), autorDto.getNome());
		assertEquals(autorFormDto.getEmail(), autorDto.getEmail());
		assertEquals(autorFormDto.getMiniCurriculo(), autorDto.getMiniCurriculo());
	}
}
