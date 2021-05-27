package com.backend.demo.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Destinatario {
	
	
	@NotBlank
	private String nome;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	private String complemento;
	@NotBlank
	private String bairro;
	
	public Destinatario() {
		// TODO Auto-generated constructor stub
	}

	public Destinatario(String nome, String logradouro, String numero, String complemento, String bairro) {
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
	}
	
	public Destinatario(Entrega entrega) {
		this.nome = entrega.getDestinatario().nome;
		this.logradouro = entrega.getDestinatario().logradouro;
		this.numero = entrega.getDestinatario().numero;
		this.complemento = entrega.getDestinatario().complemento;
		this.bairro = entrega.getDestinatario().bairro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	

}
