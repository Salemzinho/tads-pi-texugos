package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ItemPedidoModel;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Integer> {

	
}