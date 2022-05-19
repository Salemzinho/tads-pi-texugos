package com.projetoIntegrador4Texugos.projetoIntegrador4.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "compra")
public class Compra implements Serializable {
    public Compra(){
        super();
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private ClienteModel ClienteModel;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra = new Date();
    private String formaPagamento;
    private String statusPagamento;
    private Double valorTotal = 0.;
    private Double valorFrete = 0.;
    
    public Double getValorTotal(){
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal){
        this.valorTotal = valorTotal;
    }
}
