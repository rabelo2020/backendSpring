package com.backend.demo.model.entradaModel;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.backend.demo.model.Destinatario;
import com.backend.demo.model.ValidationGroups;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class EntregaInput {

	
	private BigDecimal taxa;
	
	@NotNull
	@Valid
	private DestinatarioInput destinatarioInput;
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	private ClienteInput clienteInput;
	}
