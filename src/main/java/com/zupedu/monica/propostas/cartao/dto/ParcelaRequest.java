package com.zupedu.monica.propostas.cartao.dto;

import com.zupedu.monica.propostas.cartao.Parcela;

import java.math.BigDecimal;

public class ParcelaRequest {

    private String id;
    private Integer quantidade;
    private Integer valor;

    public ParcelaRequest(String id, Integer quantidade, Integer valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getValor() {
        return valor;
    }

    public Parcela paraParcela() {
        return new Parcela(this.id, this.quantidade,BigDecimal.valueOf(this.valor));
    }
}
