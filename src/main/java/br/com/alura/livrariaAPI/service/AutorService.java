package br.com.alura.livrariaAPI.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alura.livrariaAPI.dto.AtualizacaoAutorFormDto;
import br.com.alura.livrariaAPI.dto.AutorDetalhadoDto;
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

	@Transactional
	public AutorDto atualizar(AtualizacaoAutorFormDto dto) {
		try {
			Autor autor = autorRepository.getById(dto.getId());
			autor.atualizarInformacoes(dto.getNome(), dto.getDataNascimento(), dto.getEmail(), dto.getMiniCurriculo());

			return modelMapper.map(autor, AutorDto.class);

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}

	@Transactional
	public void remover(Long id) {
		autorRepository.deleteById(id);
	}

	public AutorDetalhadoDto detalhar(Long id) {
		Autor autor = autorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(autor, AutorDetalhadoDto.class);
	}
}