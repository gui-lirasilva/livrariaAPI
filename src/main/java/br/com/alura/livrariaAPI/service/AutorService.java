package br.com.alura.livrariaAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livrariaAPI.dto.AutorDto;
import br.com.alura.livrariaAPI.dto.AutorFormDto;
import br.com.alura.livrariaAPI.modelo.Autor;

@Service
public class AutorService {
	
	private List<Autor> autores = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<AutorDto> listar() {
		return autores.stream().map(a -> modelMapper.map(a, AutorDto.class)).collect(Collectors.toList());
	}
	
	public void cadastrar(@Valid AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autores.add(autor);
	}
}
