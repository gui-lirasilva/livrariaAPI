package br.com.alura.livrariaAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livrariaAPI.dto.RelatorioLivrosAutorDto;
import br.com.alura.livrariaAPI.service.RelatorioLivrosAutorService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioLivrosAutorController {
	
	@Autowired
	private RelatorioLivrosAutorService service;
	
	@GetMapping("/autor")
	public List<RelatorioLivrosAutorDto> relatorioDeLivros(){
		return service.relatorio();
	}
}
