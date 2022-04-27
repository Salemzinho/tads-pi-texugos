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
    
    public EnderecoModel updateAddress(int id, EnderecoModel enderecoModel) {						
		Optional<EnderecoModel> op = this.enderecoRepo.findById(id);
		if(op.isPresent()){
			EnderecoModel obj = op.get();
			obj.setBairro(enderecoModel.getBairro());
			obj.setCEP(enderecoModel.getCEP());
			obj.setComplemento(enderecoModel.getComplemento());
			obj.setLocalidade(enderecoModel.getLocalidade());
			obj.setIsFaturamento(enderecoModel.getIsFaturamento());
			obj.setIsPadrao(enderecoModel.getIsPadrao());
			obj.setNumero(enderecoModel.getNumero());
			obj.setUF(enderecoModel.getUF());
			obj.setLogradouro(enderecoModel.getLogradouro());
			return this.enderecoRepo.save(obj);
		} else {
			throw new RuntimeException("Endereço não encontrado!");
		}
	}
	
	
}
