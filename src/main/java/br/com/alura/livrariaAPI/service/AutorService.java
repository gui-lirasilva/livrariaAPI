package br.com.alura.livrariaAPI.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alura.livrariaAPI.dto.AutorDto;
import br.com.alura.livrariaAPI.dto.AutorFormDto;
import br.com.alura.livrariaAPI.modelo.Autor;
import br.com.alura.livrariaAPI.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<AutorDto> listar(Pageable paginacao) {
		Page<Autor> autores = autorRepository.findAll(paginacao);
		return autores.map(a -> modelMapper.map(a, AutorDto.class));
	}
	
	@Transactional
	public AutorDto cadastrar(@RequestBody @Valid AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autor.setId(null);
		autorRepository.save(autor);
		return modelMapper.map(autor, AutorDto.class);
	}
}