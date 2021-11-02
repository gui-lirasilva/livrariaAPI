package br.com.alura.livrariaAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveriaCadastrarUmAutorComDadosCompletos() throws Exception {
		String json = "{"
				+ "\"nome\":\"Nome do autor\", "
				+ "\"dataNascimento\":\"10/10/1990\", "
				+ "\"email\":\"autor@gmail.com\", "
				+ "\"miniCurriculo\":\"Escrevo livros\"}";

		mockMvc.perform(
				post("/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				.andExpect(content().json(json));
	}
	
	@Test
	void naoDeveriaCadastrarUmAutorComDadosImcompletos() throws Exception {
		String json = "{}";

		mockMvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}
	
	 @Test
	  void naoDeveriaCadastrarUmAutorComDadosVazios() throws Exception {
	    String json = "{\"nome\":\"\", \"email\":\"\", \"dataNascimento\":\"\", \"miniCurriculo\":\"\"}";

	    mockMvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json))
	      .andExpect(status().isBadRequest());
	  }

	  @Test
	  void deveriaCadastrarUmAutorComDataNoPassado() throws Exception {
	    String dataNoPassado = LocalDate.now().minusYears(1L).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"dataNascimento\":\"" + dataNoPassado + "\", \"miniCurriculo\":\"Escrevo livros\"}";

	    mockMvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json))
	      .andExpect(status().isCreated())
	      .andExpect(header().exists("Location"))
	      .andExpect(content().json(json));
	  }

	  @Test
	  void deveriaCadastrarUmAutorComDataNoPresente() throws Exception {
	    String dataNoPresente = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"dataNascimento\":\"" + dataNoPresente + "\", \"miniCurriculo\":\"Escrevo livros\"}";

	    mockMvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json))
	      .andExpect(status().isCreated())
	      .andExpect(header().exists("Location"))
	      .andExpect(content().json(json));
	  }

	  @Test
	  void naoDeveriaCadastrarUmAutorComDataDeNascimentoFutura() throws Exception {
	    String dataNoFuturo = LocalDate.now().plusYears(1L).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"dataNascimento\":\"" + dataNoFuturo + "\", \"miniCurriculo\":\"Escrevo livros\"}";

	    mockMvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json))
	      .andExpect(status().isBadRequest());
	  }
}
