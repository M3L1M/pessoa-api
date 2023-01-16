package com.gerenciarpessoa.pessoaapi.service.impl;

import java.util.List;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.gerenciarpessoa.pessoaapi.api.dto.EnderecoDTO;
import com.gerenciarpessoa.pessoaapi.api.dto.EnderecoPrincipalDTO;
import com.gerenciarpessoa.pessoaapi.exception.RegraNegocioException;
import com.gerenciarpessoa.pessoaapi.model.entity.Endereco;
import com.gerenciarpessoa.pessoaapi.model.entity.Pessoa;
import com.gerenciarpessoa.pessoaapi.model.enums.EnderecoPrincipal;
import com.gerenciarpessoa.pessoaapi.model.repository.EnderecoRepository;
import com.gerenciarpessoa.pessoaapi.model.repository.PessoaRepository;
import com.gerenciarpessoa.pessoaapi.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EnderecoServiceImpl implements EnderecoService {

	private final EnderecoRepository repository;
	private final PessoaRepository pessoaRepository;
	
	@Override
	@Transactional
	public Endereco adicionar(Integer idPessoa, EnderecoDTO dto) {
		Endereco endereco=converter(idPessoa,dto);
		endereco.setEnderecoPrincipal(EnderecoPrincipal.NÃO);
		return repository.save(endereco);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Endereco> listar(Integer idPessoa) {
		
		Pessoa pessoa=pessoaRepository
				.findById(idPessoa)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ID não existe"));
		
		Endereco endereco=new Endereco();
		endereco.setPessoa(pessoa);
		
		Example<Endereco> example = Example.of(endereco,ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING)
			);
		
		return repository.findAll(example);
	}

	@Override 
	public void informarEnderecoPrincipal(Integer id,EnderecoPrincipalDTO dto) {
		repository
			.findById(id)
			.map(entity -> {
				EnderecoPrincipal enderecoPrincipal = EnderecoPrincipal.valueOf(dto.getEnderecoPrincipal());
				if(enderecoPrincipal == null) {
					return new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel atualizar o endereco principal, envie um valor valido ");
				}
				
				List<Endereco> endereco=repository.findAllByPessoa(entity.getPessoa());
				
				
				if(endereco.isEmpty()) {
					return new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel atualizar o endereco principal, endereco não encontrado na base de dados");
				}
				for(int i = 0; i<endereco.size();i++) {
					
					if(endereco.get(i).getEnderecoPrincipal().equals(EnderecoPrincipal.SIM)) {
						endereco.get(i).setEnderecoPrincipal(EnderecoPrincipal.NÃO);
						repository.save(endereco.get(i));
					}
				}
				
				try {
					entity.setEnderecoPrincipal(enderecoPrincipal);
					repository.save(entity);
					return HttpStatus.OK;
				}catch (RegraNegocioException e) {
					return new ResponseStatusException(HttpStatus.BAD_REQUEST,"Endereço não encontrado na base de dados");
				}
			}).orElseGet(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existe uma pessoa com este ID"));
	}
	
	private Endereco converter(Integer idPessoa,EnderecoDTO dto) {
		Endereco endereco=new Endereco();
		endereco.setCep(dto.getCep());
		endereco.setLogradouro(dto.getLogradouro());
		endereco.setNumero(dto.getNumero());
		Pessoa pessoa=pessoaRepository
				.findById(idPessoa)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existe uma pessoa com este ID"));
		endereco.setPessoa(pessoa);
		return endereco;
	}

}
