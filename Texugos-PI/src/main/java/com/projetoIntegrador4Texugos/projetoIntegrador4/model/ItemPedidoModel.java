package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_item_pedido")
public class ItemPedidoModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_pedido_seq")
	@SequenceGenerator(name = "item_pedido_seq", sequenceName = "item_pedido_seq", allocationSize = 1)
	@Column(name = "id_item_pedido")
	private Integer idItemPedido;
	
	
	@Column(name = "qtde_item_pedido")
	private Integer qtdeItemPedido;
	
	
	@Column(name = "preco_item_pedido")
	private Double PrecoItemPedido;
	
	@OneToOne
	@JoinColumn(name = "id_pedido")
	private PedidoModel pedido;
	
	@OneToOne
	@JoinColumn(name = "id_prod")
	private Produto produto;

}