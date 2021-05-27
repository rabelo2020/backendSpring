package com.backend.demo.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.backend.demo.dto.EntregaDto;
import com.backend.demo.model.Entrega;
import com.backend.demo.model.entradaModel.EntregaInput;
import com.backend.demo.teste.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ConverterEntrega {
	
	private ModelMapper modelMapper;

	
	public EntregaDto convEntregaToEntregaDto(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDto.class);
	}
	
	public Entrega convEntregaInputToEntrega(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}

	public List<EntregaDto> convListEntregaToEntregaDto(List<Entrega> entrega) {
		
		return entrega.stream().map(this::convEntregaToEntregaDto)
				      .collect(Collectors.toList());
	}


}
