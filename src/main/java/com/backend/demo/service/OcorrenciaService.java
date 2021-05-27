package com.backend.demo.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.demo.dto.OcorrenciaDto;
import com.backend.demo.exception.EntidadeNaoEncotradaException;
import com.backend.demo.model.Entrega;
import com.backend.demo.model.Ocorrencia;
import com.backend.demo.model.entradaModel.OcorrenciaInput;
import com.backend.demo.repository.OcorrenciaRepository;
import com.backend.demo.util.ConverterOcorrencia;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OcorrenciaService {

	private OcorrenciaRepository ocorrenciaRepository;
	private ConverterOcorrencia convOcorrencia;
	private EntregaService entregaService;

	public List<OcorrenciaDto> getAllOcorrencias() {

		List<Ocorrencia> list = ocorrenciaRepository.findAll();
		return convOcorrencia.listOcorrenciaToOcorrenciaDto(list);
	}

	public OcorrenciaDto getOcorrecia(Long id) {
		Ocorrencia ocorrencia = ocorrenciaRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncotradaException(String.format("Ocorrencia do código %d não existe!", id)));

		return convOcorrencia.ocorrenciaToOcorrenciaDto(ocorrencia);
	}
	/*
	 * @Transactional public Ocorrencia registrar(Long entregaId, String descricao)
	 * {
	 * 
	 * Entrega entrega = entregaService.getEntrega(entregaId);
	 * 
	 * return entrega.adicionarOcorrencia(descricao); }
	 */

	@Transactional
	public Ocorrencia registrarOcorrencia(OcorrenciaInput ocorrenciaInput) {
		Entrega entrega = entregaService.getEntrega(ocorrenciaInput.getEntregaId());
		
Ocorrencia ocorrencia =	entrega.adicionarOcorrencia(convOcorrencia.ocorrenciaInputToOcorrencia(ocorrenciaInput));

		return ocorrenciaRepository.save(ocorrencia);
	}

}
