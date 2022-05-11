package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.PedidoModel;


@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

}
