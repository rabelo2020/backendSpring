package com.backend.demo.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.demo.dto.ClienteDto;
import com.backend.demo.exception.EmailJaCadastradoException;
import com.backend.demo.exception.EntidadeNaoEncotradaException;
import com.backend.demo.exception.RequesicaoInvalidaException;
import com.backend.demo.model.Cliente;
import com.backend.demo.service.ClienteService;
import com.backend.demo.util.ClientePage;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public ResponseEntity<Page<ClienteDto>> getClientesAll(ClientePage clientePage) {
		return new ResponseEntity<Page<ClienteDto>>(clienteService.buscarClienteAll(clientePage), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> getCliente(@PathVariable Long id) {

		ClienteDto clienteDto = clienteService.buscarClienteDto(id);
		return ResponseEntity.ok(clienteDto);

	}

	@PostMapping
	public ResponseEntity<ClienteDto> saveClienteOne(@Valid @RequestBody Cliente cliente) {

		ClienteDto clienteDto = clienteService.saveCliente(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(uri).body(clienteDto);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {

		ClienteDto clienteDtoUpdate = clienteService.updateClientService(id, clienteDto);
		return new ResponseEntity<ClienteDto>(clienteDtoUpdate, HttpStatus.NO_CONTENT);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirOneCliente(@PathVariable Long id) {

		clienteService.excluirCliente(id);
		return ResponseEntity.noContent().build();

	}

}
