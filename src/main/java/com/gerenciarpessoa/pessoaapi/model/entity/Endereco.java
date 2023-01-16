package com.gerenciarpessoa.pessoaapi.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gerenciarpessoa.pessoaapi.model.enums.EnderecoPrincipal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="logradouro")
	private String logradouro;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="numero")
	private Integer numero;
	
	@Column(name="endereco_principal")
	@Enumerated(EnumType.STRING)
	private EnderecoPrincipal enderecoPrincipal;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	
	
	
	
}
