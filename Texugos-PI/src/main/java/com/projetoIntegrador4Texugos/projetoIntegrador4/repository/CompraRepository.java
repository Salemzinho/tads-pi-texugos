package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

	@Transactional
	@Modifying
	@Query(value = "SELECT c.EnderecoModel.id FROM Compra c WHERE c.id=:id")
	public int getEnderecoIdByCompra(@Param("id") int id);
	
    public List<Compra> findByClienteId(Integer clienteId);

	@Override
	default <S extends Compra> S saveAndFlush(S entity) {
		S result = save(entity);
		flush();

		return result;
	}
}
