package com.gerenciarpessoa.pessoaapi.service;

import java.util.List;

import com.gerenciarpessoa.pessoaapi.api.dto.EnderecoDTO;
import com.gerenciarpessoa.pessoaapi.api.dto.EnderecoPrincipalDTO;
import com.gerenciarpessoa.pessoaapi.model.entity.Endereco;

public interface EnderecoService {
	Endereco adicionar(Integer idPessoa,EnderecoDTO dto);
	List<Endereco> listar (Integer idPessoa);
	void informarEnderecoPrincipal(Integer id,EnderecoPrincipalDTO dto);
}
