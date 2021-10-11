package br.com.alura.livrariaAPI.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alura.livrariaAPI.dto.LivroDto;
import br.com.alura.livrariaAPI.dto.LivroFormDto;
import br.com.alura.livrariaAPI.modelo.Livro;
import br.com.alura.livrariaAPI.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivroDto> listar(Pageable paginacao){
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(l -> modelMapper.map(l, LivroDto.class));
	}
	
	@Transactional
	public LivroDto cadastrar(@RequestBody @Valid LivroFormDto dto) {
		Livro livro = modelMapper.map(dto, Livro.class);
		livro.setId(null);
		livroRepository.save(livro);
		return modelMapper.map(livro, LivroDto.class);
	}
}
