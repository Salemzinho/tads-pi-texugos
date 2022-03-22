package com.projetoIntegrador4Texugos.projetoIntegrador4.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdutoDTO {
	
	private Integer idProd;
  
	@NotNull(message = "O campo 'nomeProd' não pode ser nulo!")
	private String nomeProd;
	
	@NotNull(message = "O campo 'precoUnitProd' não pode ser nulo!")
	private double precoUnitProd;
	
	@NotNull(message = "O campo 'qtdeEstoqueProd' não pode ser nulo!")
	private int qtdeEstoqueProd;
	
}