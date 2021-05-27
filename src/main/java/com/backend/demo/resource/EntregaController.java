package com.backend.demo.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.demo.dto.EntregaDto;
import com.backend.demo.model.Entrega;
import com.backend.demo.model.StatusEntrega;
import com.backend.demo.model.entradaModel.EntregaInput;
import com.backend.demo.service.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaService entregaService;

	public EntregaController(EntregaService entregaService) {
		this.entregaService = entregaService;
	}

	@GetMapping
	public ResponseEntity<List<EntregaDto>> buscarEntregasAll() {
		List<EntregaDto> entregaDto = entregaService.buscarTodasEntregas();

		return new ResponseEntity<List<EntregaDto>>(entregaDto, HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<EntregaDto> buscarEntregaOne(@PathVariable Long id) {
			EntregaDto entregaDto = entregaService.buscarOneEntrega(id);
			return new ResponseEntity<EntregaDto>(entregaDto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Entrega> saveEntrega(@Valid @RequestBody EntregaInput entregaInput){
		Entrega entregaSave= entregaService.salvar(entregaInput);
	
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			 .buildAndExpand(entregaSave.getId()).toUri();
	
	return ResponseEntity.created(uri).body(entregaSave);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Void> updateEntrega(@PathVariable Long id, StatusEntrega status){
entregaService.atualizarEntrega(id, status);
	
	return ResponseEntity.noContent().build();
	}

}
