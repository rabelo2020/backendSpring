package com.backend.demo.util;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend.demo.dto.ClienteDto;
import com.backend.demo.model.Cliente;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
public class ConverterCliente {
	
	private ModelMapper modelMapper;
	
	public ClienteDto convClienteToClienteDto(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDto.class);
	}
	
	public Page<ClienteDto> convClienteToClienteDto(Page<Cliente> cliente){
		return cliente.map(this::convClienteToClienteDto);
	}

}
