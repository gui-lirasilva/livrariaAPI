package br.com.alura.livrariaAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class RelatorioLivrosAutorControllerTest {

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
	void deveriaRetornarRelatorioComStatus200() throws Exception {
		mockMvc.perform(
				get("/relatorios")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token))
	      .andExpect(status().isOk());
	}

}
