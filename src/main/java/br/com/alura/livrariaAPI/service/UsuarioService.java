package br.com.alura.livrariaAPI.service;

import java.util.Random;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.livrariaAPI.dto.UsuarioDto;
import br.com.alura.livrariaAPI.dto.UsuarioFormDto;
import br.com.alura.livrariaAPI.modelo.Perfil;
import br.com.alura.livrariaAPI.modelo.Usuario;
import br.com.alura.livrariaAPI.repository.PerfilRepository;
import br.com.alura.livrariaAPI.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PerfilRepository perfilRepository;
    
	public Page<UsuarioDto> listar(Pageable paginacao) {

		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t, UsuarioDto.class));
	}
	
	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		usuario.setId(null);

		Perfil perfil = perfilRepository.getById(dto.getPerfilId());

		usuario.adicionarPerfil(perfil);

		String senha = new Random().nextInt(999999) + "";

		usuario.setSenha(bCryptPasswordEncoder.encode(senha));

		System.out.println(usuario.getSenha()); // Para saber a senha gerada

		usuarioRepository.save(usuario);

		return modelMapper.map(usuario, UsuarioDto.class);
	}
}
