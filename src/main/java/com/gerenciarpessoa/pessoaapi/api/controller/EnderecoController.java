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

import com.gerenciarpessoa.pessoaapi.api.dto.EnderecoDTO;
import com.gerenciarpessoa.pessoaapi.api.dto.EnderecoPrincipalDTO;
import com.gerenciarpessoa.pessoaapi.model.entity.Endereco;
import com.gerenciarpessoa.pessoaapi.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

	private final EnderecoService service;
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer adicionar(@PathVariable Integer id,@RequestBody EnderecoDTO dto) {
		Endereco endereco=service.adicionar(id, dto);
		return endereco.getId();
	}
	
	@GetMapping("/{id}")
	public List<Endereco> listar(@PathVariable Integer id){
		return service.listar(id);
	}
	
	@PutMapping("/{id}/endereco-principal")
	public void informarEnderecoPrincipal(@PathVariable("id") Integer id,@RequestBody EnderecoPrincipalDTO dto) {
		service.informarEnderecoPrincipal(id,dto);
	}
}
