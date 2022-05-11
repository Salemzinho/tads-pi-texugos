package com.projetoIntegrador4Texugos.projetoIntegrador4.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer>{
    	
	public List<EnderecoModel> findByClienteId(Integer clienteId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE EnderecoModel e SET e.isPadrao=FALSE WHERE e.cliente.id = :idCliente AND e.id != :idEnderecoPadrao")
	public void mudarEnderecoPadrao(@Param("idEnderecoPadrao") int idEnderecoPadrao, @Param("idCliente") int idCliente);
}
