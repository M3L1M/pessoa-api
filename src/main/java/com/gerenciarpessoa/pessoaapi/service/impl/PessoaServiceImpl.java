package com.gerenciarpessoa.pessoaapi.service.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gerenciarpessoa.pessoaapi.api.dto.PessoaDTO;
import com.gerenciarpessoa.pessoaapi.model.entity.Pessoa;
import com.gerenciarpessoa.pessoaapi.model.repository.PessoaRepository;
import com.gerenciarpessoa.pessoaapi.service.PessoaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {

	private final PessoaRepository repository;

	@Override
	@Transactional
	public Pessoa adicionar(PessoaDTO dto) {
		Pessoa pessoa = converter(dto);
		return repository.save(pessoa);
	}

	@Override
	@Transactional
	public Pessoa editar(Integer id, PessoaDTO dto) {
		Pessoa pessoa=converter(dto);
		return repository
				.findById(id)
				.map(entity -> {
					pessoa.setId(entity.getId());
					
					return repository.save(pessoa);
				}).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Este ID não existe"));
	}

	@Override
	@Transactional
	public Pessoa obterPorId(Integer id){
		
		return repository
				.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Este ID não existe"));
	}
	
	@Override
	public List<Pessoa> listar() {
		
		return repository.findAll();
	}
	
	private Pessoa converter(PessoaDTO dto) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(dto.getNome());
		pessoa.setDataNascimento(dto.getDataNascimento());
		return pessoa;
	}

	

	
	
}
