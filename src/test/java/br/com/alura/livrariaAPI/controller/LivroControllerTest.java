package br.com.alura.livrariaAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.alura.livrariaAPI.dto.AutorDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LivroControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private AutorDto cadastrarAutor() throws Exception {
		String jsonAutor = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"dataNascimento\":\"01/01/2000\", \"miniCurriculo\":\"Escrevo livros\"}";

		MvcResult mvcResult = mockMvc
				.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(jsonAutor))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(content().json(jsonAutor)).andReturn();

		String autor = mvcResult.getResponse().getContentAsString();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		return objectMapper.readValue(autor, AutorDto.class);
	}
	
	@Test
	void deveriaRetornarLivros() throws Exception {
		mockMvc.perform(get("/livros").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	void deveriaCadastrarLivroComDadosCompletos() throws Exception {
		AutorDto autorDto = cadastrarAutor();

		String jsonLivro = "{\"titulo\":\"Livro teste\",\"dataLancamento\":\"01/01/2001\",\"numeroDePaginas\": 300,\"autorId\":"
				+ autorDto.getId() + "}";

		mockMvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content(jsonLivro))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"));
	}
	
	@Test
	void naoDeveriaCadastrarLivrosComDadosImcompletos() throws Exception {
		cadastrarAutor();

		String jsonLivro = "{}";

		mockMvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content(jsonLivro))
				.andExpect(status().isBadRequest());
	}
	
}
