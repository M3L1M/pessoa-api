package com.gerenciarpessoa.pessoaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciarpessoa.pessoaapi.model.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
