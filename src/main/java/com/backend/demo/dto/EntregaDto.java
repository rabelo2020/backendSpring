package com.backend.demo.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.backend.demo.model.StatusEntrega;
import com.backend.demo.teste.ClienteResumoModel;
import com.backend.demo.teste.DestinatarioModel;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class EntregaDto {
	
	private Long id;
	private ClienteDto cliente;
	private DestinatarioDto destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	

		}
