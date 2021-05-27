package com.backend.demo.model.entradaModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.backend.demo.model.Entrega;
import com.backend.demo.model.ValidationGroups;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OcorrenciaInput {
	
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Long entregaId;
	
	}
