package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Compra;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.CompraRepository;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository repository;
	
	public List<Compra> findAll(){
		List<Compra> list = this.repository.findAll();
		return list;//stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList :: new));
	}
	
	public Compra findOne(Integer id) throws Exception {
		return this.repository.findById(id)
					.orElseThrow(() -> new Exception("Matrícula não localizada"));
	}
	
	public Compra update(Integer id, Compra compra) {						
		Optional<Compra> op = this.repository.findById(id);
		if(op.isPresent()){
			Compra obj = op.get();
			obj.setStatusPagamento(compra.getStatusPagamento());

			return this.repository.save(obj);
		} else {
			throw new RuntimeException("Produto não encontrado!");
		}
	}


	public int getIdEndereco(int id) {
		return this.repository.getEnderecoIdByCompra(id);
	}



	public List<Compra> findByCodCliente(Integer clienteId){
    	return this.repository.findByClienteId(clienteId);
    }






	public Compra save(Compra novo) {
		return this.repository.save(novo);
	}

	public Compra update(int id, Compra compra) {						
		Optional<Compra> op = this.repository.findById(id);
		if(op.isPresent()){
			Compra obj = op.get();
			obj.setStatusPagamento(compra.getStatusPagamento());

			return this.repository.save(obj);
		} else {
			throw new RuntimeException("Produto não encontrado!");
		}
	}
	
	public void delete(int id) {	
		this.repository.deleteById(id);
	}
}