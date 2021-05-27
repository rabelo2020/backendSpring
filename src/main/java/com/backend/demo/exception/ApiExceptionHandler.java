package com.backend.demo.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	private MessageSource messageSource;

	@Override //Validação de Campos na Entidade
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problema.Campo> campos = new ArrayList<>();

		for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) erro).getField();
			String msg = messageSource.getMessage(erro, LocaleContextHolder.getLocale());

			campos.add(new Problema.Campo(nome, msg));
		}
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setData(OffsetDateTime.now());
		problema.setTitulo("Uma ou mas campos estãos inválidos");
		problema.setCampos(campos);

		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler({NegocioException.class})
	public ResponseEntity<Object> handlerNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setData(OffsetDateTime.now());
		problema.setTitulo(ex.getLocalizedMessage());
		//problema.setCampos(campos);

		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncotradaException.class)
	public ResponseEntity<Object> handlerEntidadeNaoEncotrada(EntidadeNaoEncotradaException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setData(OffsetDateTime.now());
		problema.setTitulo(ex.getLocalizedMessage());

		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EmailJaCadastradoException.class)
	public ResponseEntity<Object> handlerEmailJaCadastradoException(EmailJaCadastradoException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setData(OffsetDateTime.now());
		problema.setTitulo(ex.getLocalizedMessage());

		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}


}
