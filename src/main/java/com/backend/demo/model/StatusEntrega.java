package com.backend.demo.model;

public enum StatusEntrega {

	
	PENDENTE("Pedente"),
	FINALIZADA("Finalizada"),
	CANCELADA("Cancelada");
	
	private String valorEnum;
	
	private StatusEntrega(String valorEnum) {
		this.valorEnum = valorEnum;
	}

	public String getValorEnum() {
		return valorEnum;
	}
	
	
}
