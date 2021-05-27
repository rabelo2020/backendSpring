package com.backend.demo.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @NotNull(groups = ValidationGroups.ClienteId.class)
	private Long id;
	private BigDecimal taxa;

	private OffsetDateTime dataPedido;

	// @JsonProperty(access = Access.READ_ONLY) Somente para Leitura
	private OffsetDateTime dataFinalizacao;

	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Embedded
	private Destinatario destinatario;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();

	public Entrega() {
		// TODO Auto-generated constructor stub
	}

	public Entrega(Long id, BigDecimal taxa, OffsetDateTime dataPedido, OffsetDateTime dataFinalizacao,
			StatusEntrega status, Cliente cliente, Destinatario destinatario) {

		this.id = id;
		this.taxa = taxa;
		this.dataPedido = dataPedido;
		this.dataFinalizacao = dataFinalizacao;
		this.status = status;
		this.cliente = cliente;
		this.destinatario = destinatario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public OffsetDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(OffsetDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public StatusEntrega getStatus() {
		return status;
	}

	public void setStatus(StatusEntrega status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}
	
	public Ocorrencia adicionarOcorrencia(Ocorrencia ocor) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(ocor.getDescricao());
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);

		this.getOcorrencias().add(ocorrencia);

		return ocorrencia;
	}


	/*
	 * public Ocorrencia adicionarOcorrencia(String descricao) { Ocorrencia
	 * ocorrencia = new Ocorrencia(); ocorrencia.setDescricao(descricao);
	 * ocorrencia.setDataRegistro(OffsetDateTime.now());
	 * ocorrencia.setEntrega(this);
	 * 
	 * this.getOcorrencias().add(ocorrencia);
	 * 
	 * return ocorrencia; }
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
