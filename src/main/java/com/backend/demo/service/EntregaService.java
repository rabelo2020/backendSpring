package com.backend.demo.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.demo.dto.EntregaDto;
import com.backend.demo.exception.EntidadeNaoEncotradaException;
import com.backend.demo.exception.NegocioException;
import com.backend.demo.model.Entrega;
import com.backend.demo.model.StatusEntrega;
import com.backend.demo.model.entradaModel.EntregaInput;
import com.backend.demo.repository.EntregaRepository;
import com.backend.demo.teste.EntregaModel;
import com.backend.demo.util.ConverterEntrega;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

	private EntregaRepository entregaRepository;
	private ClienteService clienteService;
	private ConverterEntrega convEntrega;

	public List<EntregaDto> buscarTodasEntregas() {
		List<Entrega> entrega = entregaRepository.findAll();
		return convEntrega.convListEntregaToEntregaDto(entrega);
		// EntregaDto(x)).collect(Collectors.toList());

	}

	public EntregaDto buscarOneEntrega(Long id) {
		Entrega entrega = entregaRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncotradaException(String.format("Entrega não existe com esse código %d !", id)));

		return convEntrega.convEntregaToEntregaDto(entrega);
	}

	public Entrega getEntrega(Long id) {
		return entregaRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncotradaException(String.format("Entrega não existe com esse código %d !", id)));

	}

	@Transactional
	public Entrega salvar(EntregaInput entregaInput) {
		Entrega entrega = convEntrega.convEntregaInputToEntrega(entregaInput);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return entregaRepository.save(entrega);

	}

	@SuppressWarnings("unlikely-arg-type")
	@Transactional
	public void atualizarEntrega(Long id, StatusEntrega status) {
		Entrega entregaUpdate = entregaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncotradaException("Entrega não encotrada"));
		if (!entregaUpdate.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega já Finalizada ou Cancelada!");
		}
		if (entregaUpdate.getStatus() == StatusEntrega.PENDENTE && status == StatusEntrega.PENDENTE) {
			throw new NegocioException("Entrega já está Status PENDENDE!");

		}

		/*
		 * if (entregaUpdate.getStatus().equals(StatusEntrega.PENDENTE) &&
		 * status.equals(StatusEntrega.PENDENTE)) { throw new
		 * NegocioException("Entrega já está Status PENDENDE!");
		 * 
		 * }
		 */
		entregaUpdate.setStatus(status);
		entregaUpdate.setDataFinalizacao(OffsetDateTime.now());

		entregaRepository.save(entregaUpdate);
		//return convEntrega.convEntregaToEntregaDto(entregaAtualizada);

	}

}
