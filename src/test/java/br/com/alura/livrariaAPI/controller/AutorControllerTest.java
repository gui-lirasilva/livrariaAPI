package br.com.alura.livrariaAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.livrariaAPI.infra.security.TokenService;
import br.com.alura.livrariaAPI.modelo.Perfil;
import br.com.alura.livrariaAPI.modelo.Usuario;
import br.com.alura.livrariaAPI.repository.PerfilRepository;
import br.com.alura.livrariaAPI.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {

	public String token;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
	public void gerarToken() {
		Usuario logado = new Usuario("Nome do usuario", "usuario@gmail.com", "123456");
		Perfil admin = perfilRepository.findById(1L).orElse(null);
		logado.adicionarPerfil(admin);
		usuarioRepository.save(logado);
		Authentication authentication = new UsernamePasswordAuthenticationToken(logado, logado.getLogin());
		this.token = tokenService.gerarToken(authentication);
	}
	
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
				.content(json)
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isCreated())
		.andExpect(header().exists("Location"))
		.andExpect(content().json(json));
	}
	
	@Test
	void naoDeveriaCadastrarUmAutorComDadosImcompletos() throws Exception {
		String json = "{}";

		mockMvc.perform(
				post("/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.header("Authorization", "Bearer " + token))
	      .andExpect(status().isBadRequest());
	}
	
	 @Test
	  void naoDeveriaCadastrarUmAutorComDadosVazios() throws Exception {
	    String json = "{\"nome\":\"\", \"email\":\"\", \"dataNascimento\":\"\", \"miniCurriculo\":\"\"}";

	    mockMvc.perform(
	    		post("/autores")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(json)
	    		.header("Authorization", "Bearer " + token))
	      .andExpect(status().isBadRequest());
	  }

	  @Test
	  void deveriaCadastrarUmAutorComDataNoPassado() throws Exception {
	    String dataNoPassado = LocalDate.now().minusYears(1L).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"dataNascimento\":\"" + dataNoPassado + "\", \"miniCurriculo\":\"Escrevo livros\"}";

	    mockMvc.perform(
	    		post("/autores")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(json)
	    		.header("Authorization", "Bearer " + token))
	      .andExpect(status().isCreated())
	      .andExpect(header().exists("Location"))
	      .andExpect(content().json(json));
	  }

	  @Test
	  void deveriaCadastrarUmAutorComDataNoPresente() throws Exception {
	    String dataNoPresente = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"dataNascimento\":\"" + dataNoPresente + "\", \"miniCurriculo\":\"Escrevo livros\"}";

	    mockMvc.perform(
	    		post("/autores")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(json)
	    		.header("Authorization", "Bearer " + token))
	      .andExpect(status().isCreated())
	      .andExpect(header().exists("Location"))
	      .andExpect(content().json(json));
	  }

	  @Test
	  void naoDeveriaCadastrarUmAutorComDataDeNascimentoFutura() throws Exception {
	    String dataNoFuturo = LocalDate.now().plusYears(1L).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"dataNascimento\":\"" + dataNoFuturo + "\", \"miniCurriculo\":\"Escrevo livros\"}";

	    mockMvc.perform(
	    		post("/autores")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(json)
	    		.header("Authorization", "Bearer " + token))
	      .andExpect(status().isBadRequest());
	  }
}
