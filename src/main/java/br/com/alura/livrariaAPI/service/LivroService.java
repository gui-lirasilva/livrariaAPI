package br.com.alura.livrariaAPI.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livrariaAPI.dto.AtualizacaoLivroFormDto;
import br.com.alura.livrariaAPI.dto.LivroDetalhadoDto;
import br.com.alura.livrariaAPI.dto.LivroDto;
import br.com.alura.livrariaAPI.dto.LivroFormDto;
import br.com.alura.livrariaAPI.modelo.Autor;
import br.com.alura.livrariaAPI.modelo.Livro;
import br.com.alura.livrariaAPI.repository.AutorRepository;
import br.com.alura.livrariaAPI.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivroDto> listar(Pageable paginacao){
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(l -> modelMapper.map(l, LivroDto.class));
	}
	
	@Transactional
	public LivroDto cadastrar(LivroFormDto livroFormDto) {
		Long autorId = livroFormDto.getAutorId();

		try {
			autorRepository.getById(autorId);

			Livro livro = modelMapper.map(livroFormDto, Livro.class);
			livro.setId(null);

			livroRepository.save(livro);
			return modelMapper.map(livro, LivroDto.class);

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
	
	@Transactional
	public LivroDto atualizar(AtualizacaoLivroFormDto dto) {
		try {
			Autor autor = autorRepository.findById(
					dto.getAutorId())
					.orElseThrow(EntityNotFoundException::new);
			
			Livro livro = livroRepository.findById(
					dto.getId())
					.orElseThrow(EntityNotFoundException::new);

			livro.atualizarInformacoes(
					dto.getTitulo(), 
					autor, 
					dto.getNumeroDePaginas(), 
					dto.getDataLancamento());

			return modelMapper.map(livro, LivroDto.class);
			
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
	
	@Transactional
	public void remover(Long id) {
		livroRepository.deleteById(id);
	}
	
	public LivroDetalhadoDto detalhar(Long id) {
	    Livro livro = livroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	    return modelMapper.map(livro, LivroDetalhadoDto.class);
	  }
}
