package com.gerenciarpessoa.pessoaapi.api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
	private String nome;
	private LocalDate dataNascimento;
	//private List<EnderecoDTO> endereco;
}
