package com.gerenciarpessoa.pessoaapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciarpessoa.pessoaapi.api.dto.PessoaDTO;
import com.gerenciarpessoa.pessoaapi.model.entity.Pessoa;
import com.gerenciarpessoa.pessoaapi.service.PessoaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
	private final PessoaService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer adicionar(@RequestBody PessoaDTO dto) {
		Pessoa pessoa=service.adicionar(dto);
		return pessoa.getId();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Integer editar(@PathVariable Integer id,@RequestBody PessoaDTO dto) {
		Pessoa pessoa=service.editar(id, dto);
		return pessoa.getId();
	}
	
	@GetMapping("/{id}")
	public Pessoa obterPorId(@PathVariable Integer id) {
		Pessoa pessoa=service.obterPorId(id);
		return pessoa;
	}
	
	@GetMapping
	public List<Pessoa> listar(){
		return service.listar();
	}
	
	
	
}
