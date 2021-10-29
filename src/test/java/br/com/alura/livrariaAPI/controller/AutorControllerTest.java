package br.com.alura.livrariaAPI.controller;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
				+ "\"data-Nascimento\":\"10/10/1990\", "
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

}
