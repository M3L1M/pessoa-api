package com.gerenciarpessoa.pessoaapi.service;

import java.util.List;

import com.gerenciarpessoa.pessoaapi.api.dto.PessoaDTO;
import com.gerenciarpessoa.pessoaapi.model.entity.Pessoa;

public interface PessoaService {
	Pessoa adicionar(PessoaDTO dto);
	Pessoa editar(Integer id,PessoaDTO dto);
	Pessoa obterPorId(Integer id);
	List<Pessoa> listar();
}
