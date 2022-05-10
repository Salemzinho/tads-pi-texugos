package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ProdutoRepository;


@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll(){
		List<Produto> list = this.repository.findAll();
		return list;//stream().map(x -> x.toDTO()).collect(Collectors.toCollection(ArrayList :: new));
	}
	
	public Produto findOne(int idProduto) throws Exception {
		return this.repository.findById(idProduto)
					.orElseThrow(() -> new Exception("Matrícula não localizada"));
	}
	
/*
	public List<Produto> findOneByName(String nomeProduto) throws Exception {
		return this.repository.findProdutoByName(nomeProduto);
	}
*/
	public Produto save(Produto novo) {
		return this.repository.save(novo);
	}

	public Produto update(int id, Produto produto) {						
		Optional<Produto> op = this.repository.findById(id);
		if(op.isPresent()){
			Produto obj = op.get();
			obj.setNomeProd(produto.getNomeProd());
			obj.setPrecoUnitProd(produto.getPrecoUnitProd());
			obj.setQtdeEstoqueProd(produto.getQtdeEstoqueProd());
			
			return this.repository.save(obj);
		} else {
			throw new RuntimeException("Produto não encontrado!");
		}
	}
	
	public void delete(int id) {	
		this.repository.deleteById(id);
	}

}