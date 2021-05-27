package com.backend.demo.exception;

public class EntidadeNaoEncotradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncotradaException(String msg) {
		super(msg);
	}

}
