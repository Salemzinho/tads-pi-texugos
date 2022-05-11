package com.projetoIntegrador4Texugos.projetoIntegrador4.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.EnderecoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.EnderecoRepository;


@Service
public class EnderecoService {

    @Autowired
	private EnderecoRepository enderecoRepo;

    public List<EnderecoModel> findAll() {
		List<EnderecoModel> list = this.enderecoRepo.findAll();
		return list;
	}
    
    public List<EnderecoModel> findByCodCliente(Integer clienteId){
    	return this.enderecoRepo.findByClienteId(clienteId);
    }
    
    public EnderecoModel updateAddress(int id, EnderecoModel enderecoModel) {						
		Optional<EnderecoModel> op = this.enderecoRepo.findById(id);
		if(op.isPresent()){
			EnderecoModel obj = op.get();
			obj.setBairro(enderecoModel.getBairro());
			obj.setCEP(enderecoModel.getCEP());
			obj.setComplemento(enderecoModel.getComplemento());
			obj.setLocalidade(enderecoModel.getLocalidade());
			obj.setIsPadrao(enderecoModel.getIsPadrao());
			obj.setNumero(enderecoModel.getNumero());
			obj.setUF(enderecoModel.getUF());
			obj.setLogradouro(enderecoModel.getLogradouro());
			return this.enderecoRepo.save(obj);
		} else {
			throw new RuntimeException("Endereço não encontrado!");
		}
	}
    
    public EnderecoModel save (EnderecoModel end) {
    	return enderecoRepo.save(end);
    }
    
    public void delete(int id) {
    	enderecoRepo.deleteById(id);
    }

	public EnderecoModel findById(int id) {
		return enderecoRepo.findById(id).get();
	}
	
	public void mudarEnderecoPadrao(int idEnderecoPadrao, int idCliente) {
		enderecoRepo.mudarEnderecoPadrao(idEnderecoPadrao, idCliente);
	}
	
}
