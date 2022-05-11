package com.projetoIntegrador4Texugos.projetoIntegrador4.dto;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PedidoNewDTO {
	private Integer idPedido;
	
	@NotNull
	private Double totalPedido;
	
	private UsuarioDTOIn usuario;

	}