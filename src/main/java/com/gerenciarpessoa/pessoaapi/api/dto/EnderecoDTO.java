package com.gerenciarpessoa.pessoaapi.api.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
	private String logradouro;
	private String cep;
	private Integer numero;
	private String enderecoPrincipal;
}
