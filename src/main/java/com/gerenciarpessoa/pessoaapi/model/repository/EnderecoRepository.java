package com.gerenciarpessoa.pessoaapi.model.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciarpessoa.pessoaapi.model.entity.Endereco;
import com.gerenciarpessoa.pessoaapi.model.entity.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{


	List<Endereco> findAllByPessoa(Pessoa pessoa);


}
