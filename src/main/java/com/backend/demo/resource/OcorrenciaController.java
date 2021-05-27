package com.backend.demo.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.demo.dto.OcorrenciaDto;
import com.backend.demo.model.Ocorrencia;
import com.backend.demo.model.entradaModel.OcorrenciaInput;
import com.backend.demo.service.OcorrenciaService;
import com.backend.demo.util.ConverterOcorrencia;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
//@RequestMapping("/entregas/{entregaId}/ocorrencias")
@RequestMapping("/ocorrencias")
@AllArgsConstructor
public class OcorrenciaController {

	private OcorrenciaService ocorrenciaService;
	private ConverterOcorrencia convOcorrencia;

	@GetMapping
	public ResponseEntity<List<OcorrenciaDto>> getOcorrenciaAll() {

		return ResponseEntity.ok().body(ocorrenciaService.getAllOcorrencias());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OcorrenciaDto> getOcorrencia(@PathVariable Long id) {

		OcorrenciaDto ocorrenciaDto = ocorrenciaService.getOcorrecia(id);
		return ResponseEntity.ok(ocorrenciaDto);
	}
	@PostMapping	
	public ResponseEntity<OcorrenciaDto> registrar(@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		
		Ocorrencia ocorrenciaRegistrada = ocorrenciaService
				.registrarOcorrencia(ocorrenciaInput);
		
		return ResponseEntity.ok().body(convOcorrencia.ocorrenciaToOcorrenciaDto(ocorrenciaRegistrada));
		
	}

	/*
	 * @PostMapping
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public OcorrenciaDto
	 * registrar(@PathVariable Long entregaId,
	 * 
	 * @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
	 * 
	 * Ocorrencia ocorrenciaRegistrada = ocorrenciaService .registrar(entregaId,
	 * ocorrenciaInput.getDescricao());
	 * 
	 * return converterOcorrencia.ocorrenciaToOcorrenciaDto(ocorrenciaRegistrada);
	 * 
	 * }
	 */
}
