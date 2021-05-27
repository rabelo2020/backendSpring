package com.backend.demo.model.entradaModel;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DestinatarioInput {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	private String complemento;
	@NotBlank
	private String bairro;
	

}
