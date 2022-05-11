package com.projetoIntegrador4Texugos.projetoIntegrador4.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_pedido")
public class PedidoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
	@SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_seq", allocationSize = 1)
	@Column(name = "id_pedido")
	private Integer idPedido;

	@Column(name = "data_pedido")
	@CreationTimestamp
	private LocalDateTime dataPedido;

	@Column(name = "total_pedido")
	private Double totalPedido;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	List<ItemPedidoModel> itensPedido = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

}