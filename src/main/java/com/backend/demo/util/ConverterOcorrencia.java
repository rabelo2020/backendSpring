package com.backend.demo.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend.demo.dto.OcorrenciaDto;
import com.backend.demo.model.Cliente;
import com.backend.demo.model.Ocorrencia;
import com.backend.demo.model.entradaModel.OcorrenciaInput;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ConverterOcorrencia {

	private ModelMapper modelMapper;

	public OcorrenciaDto ocorrenciaToOcorrenciaDto(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDto.class);
	}

	public List<OcorrenciaDto> listOcorrenciaToOcorrenciaDto(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::ocorrenciaToOcorrenciaDto).collect(Collectors.toList());

	}

	public Ocorrencia ocorrenciaInputToOcorrencia(OcorrenciaInput ocorrenciaInput) {
		return modelMapper.map(ocorrenciaInput, Ocorrencia.class);
	}

}
