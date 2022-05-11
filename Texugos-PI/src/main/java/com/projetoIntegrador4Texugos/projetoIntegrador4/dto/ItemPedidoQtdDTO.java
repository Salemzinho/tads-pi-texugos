package com.projetoIntegrador4Texugos.projetoIntegrador4.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemPedidoQtdDTO {
	
	@NotNull
	private Integer qtdeItemPedido;
}