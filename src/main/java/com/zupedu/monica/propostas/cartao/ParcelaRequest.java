package com.zupedu.monica.propostas.cartao;

import java.math.BigDecimal;

public class ParcelaRequest {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public ParcelaRequest(String id, Integer quantidade, BigDecimal valor) {
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

    public BigDecimal getValor() {
        return valor;
    }
}
