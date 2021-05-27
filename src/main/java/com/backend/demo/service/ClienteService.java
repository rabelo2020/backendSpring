package com.backend.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.demo.dto.ClienteDto;
import com.backend.demo.exception.EmailJaCadastradoException;
import com.backend.demo.exception.EntidadeNaoEncotradaException;
import com.backend.demo.exception.RequesicaoInvalidaException;
import com.backend.demo.model.Cliente;
import com.backend.demo.repository.ClienteRepository;
import com.backend.demo.util.ClientePage;
import com.backend.demo.util.ConverterCliente;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

	private ClienteRepository clienteRepository;
	private ConverterCliente converterCliente;

	public Page<ClienteDto> buscarClienteAll(ClientePage clientePage) {
		Sort sort = Sort.by(clientePage.getSortDirection(), clientePage.getSortBy());
		Pageable pageable = PageRequest.of(clientePage.getPageNumber(), clientePage.getPageSize(), sort);

		Page<Cliente> cliente = clienteRepository.findAll(pageable);

		return converterCliente.convClienteToClienteDto(cliente);
	}

	public ClienteDto buscarClienteDto(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncotradaException(String.format("Cliente do código %d não cadastrado!", id)));
		return converterCliente.convClienteToClienteDto(cliente);
	}

	@Transactional
	public ClienteDto saveCliente(Cliente cliente) {

		isEmail(cliente);

		Cliente clienteSave = clienteRepository.save(cliente);

		return converterCliente.convClienteToClienteDto(clienteSave);
	}

	private void isEmail(Cliente cliente) {
		boolean isEmail = clienteRepository.existsByEmail(cliente.getEmail().trim());

		if (isEmail && cliente.getId() == null) {
			throw new EmailJaCadastradoException(
					String.format("E-mail %s já cadastrado, ensira outro!", cliente.getEmail()));
		}

	}

	@Transactional
	public ClienteDto updateClientService(Long id, ClienteDto clienteDto) {

		if (!clienteRepository.existsById(id)) {
			throw new EntidadeNaoEncotradaException(String.format("Cliente com código %d não existe!", id));
		}
		Cliente clienteUpdate = clienteRepository.getOne(id);
		System.out.println("BD: " + clienteUpdate);
		System.out.println("Swagger:\nNome: " + clienteDto.getNome() + " E-mail: " + clienteDto.getEmail()
				+ " Telefone: " + clienteDto.getTelefone());

		Cliente newCliente = validarFieldsCliente(clienteDto, clienteUpdate);
		System.out.println("ClientUpdate" + newCliente);
		return saveCliente(newCliente);

	}

	private Cliente validarFieldsCliente(ClienteDto clienteDto, Cliente clienteUpdate) {

		if (clienteDto.getNome() != null) {
			clienteUpdate.setNome(clienteDto.getNome());
		}
		if (clienteDto.getEmail() != null) {
			clienteUpdate.setEmail(clienteDto.getEmail());

		}
		if (clienteDto.getTelefone() != null) {
			clienteUpdate.setTelefone(clienteDto.getTelefone());

		}

		return clienteUpdate;
	}

	@Transactional
	public void excluirCliente(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isEmpty()) {
			throw new EntidadeNaoEncotradaException(String.format("Código %d não encontrado!", id));
		}
		clienteRepository.deleteById(id);
	}

	public Cliente buscarCliente(Long id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncotradaException(String.format("Cliente do código %d não cadastrado!", id)));

	}

}
