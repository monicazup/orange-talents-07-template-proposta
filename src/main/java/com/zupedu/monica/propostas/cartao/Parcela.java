package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.cartao.dto.ParcelaRequest;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Parcela {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public Parcela(ParcelaRequest request){
        this.id = request.getId();
        this.quantidade = request.getQuantidade();
        this.valor = BigDecimal.valueOf(request.getValor());
    }


    @Deprecated
    public Parcela() {
    }

    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;


    }
}
