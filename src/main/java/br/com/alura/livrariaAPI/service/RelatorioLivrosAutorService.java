package br.com.alura.livrariaAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.livrariaAPI.dto.RelatorioLivrosAutorDto;
import br.com.alura.livrariaAPI.repository.LivroRepository;

@Service
public class RelatorioLivrosAutorService {
	
	@Autowired
	private LivroRepository repository;
	
	public List<RelatorioLivrosAutorDto> relatorio(){
		return repository.relatorioDeLivros();
	}
}
