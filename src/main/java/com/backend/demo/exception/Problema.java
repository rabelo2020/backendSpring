package com.backend.demo.exception;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problema {

	private Integer status;
	private OffsetDateTime data;
	private String titulo;
	private List<Campo> campos;

	@AllArgsConstructor
	@Getter
	public static class Campo {
		private String campo;
		private String messagem;

	}

}
