package com.zupedu.monica.propostas.cartao;

import java.math.BigDecimal;

public class Parcela {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public Parcela(ParcelaRequest request){
        this.id = request.getId();
        this.quantidade = request.getQuantidade();
        this.valor = request.getValor();
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
