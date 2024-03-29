package com.projetoIntegrador4Texugos.projetoIntegrador4.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "itens_compra")
public class ItensCompraModel implements Serializable {
    public ItensCompraModel(){
        super();
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Compra compra;

    private Integer quantidade;
    private Double valorUnitario = 0.;
    private Double valorTotal = 0.;


    public Produto getProduto(){
        return produto;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public Integer getQuantidade(){
        if (quantidade == null){
            quantidade = 0;
        }
        return quantidade;
    }

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }



}
