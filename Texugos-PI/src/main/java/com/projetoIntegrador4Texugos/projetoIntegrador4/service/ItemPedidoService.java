package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoIntegrador4Texugos.projetoIntegrador4.dto.ItemPedidoQtdDTO;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ItemPedidoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.PedidoModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Produto;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ItemPedidoRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.PedidoRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public ItemPedidoModel save(ItemPedidoModel newItemPedido) {
		Integer idProd = newItemPedido.getProduto().getIdProd();
		Integer idPed = newItemPedido.getPedido().getIdPedido();
		
		Optional<Produto> objProd = this.produtoRepository.findById(idProd);
		Optional<PedidoModel> objPed = this.pedidoRepository.findById(idPed);
		
		if(objProd.isPresent() && objPed.isPresent()) {
			double preco = newItemPedido.getProduto().getPrecoUnitProd();
			
			ItemPedidoModel model = newItemPedido;
			
			model.setPrecoItemPedido(preco);
			model.setProduto(objProd.get());
			model.setPedido(objPed.get());
			
			return this.itemPedidoRepository.save(model);
		} else {
			return null;
		}
	}
  
	public List<ItemPedidoModel> findAll(){
		List<ItemPedidoModel> list = this.itemPedidoRepository.findAll();
		return list;
	}
	
	public void delete(int id) {
		this.itemPedidoRepository.deleteById(id);
	}
	
	/*
	 * public Page<ItemPedidoModel> paginacao(int pagina, int registros){
	 * PageRequest pageRequest = PageRequest.of(pagina, registros);
	 * Page<ItemPedidoModel> pageModel =
	 * this.itemPedidoRepository.findAll(pageRequest);
	 * 
	 * Page<ItemPedidoModel> pageDTO = pageModel.map( new Function<ItemPedidoModel,
	 * ItemPedidoModel>(){ public ItemPedidoModel apply(ItemPedidoModel model) {
	 * return model; } } ); return pageDTO; }
	 */
	public ItemPedidoModel finOne(int itemPedido) throws Exception {
		return this.itemPedidoRepository.findById(itemPedido).orElseThrow(() -> new Exception("Pedido não encontrado"));
	}
	
	
	public ItemPedidoModel update(int id, ItemPedidoQtdDTO newObj) {
		Optional<ItemPedidoModel> optObj = this.itemPedidoRepository.findById(id);
		
		if (optObj.isPresent()) {
			ItemPedidoModel obj = optObj.get();
			
			obj.setQtdeItemPedido(newObj.getQtdeItemPedido());
			
			return this.itemPedidoRepository.save(obj);
		}else {
			throw new RuntimeException("Pedido não encontrado");
		}

	}
}