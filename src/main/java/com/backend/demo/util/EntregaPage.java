package com.backend.demo.util;

import org.springframework.data.domain.Sort;

public class EntregaPage {
	
	private int pageNumber =0;
	private int sizeNumber =5;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy ="dataPedido";
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getSizeNumber() {
		return sizeNumber;
	}
	public void setSizeNumber(int sizeNumber) {
		this.sizeNumber = sizeNumber;
	}
	public Sort.Direction getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
	

}
