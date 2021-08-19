package com.zupedu.monica.propostas.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoRequest {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public RenegociacaoRequest(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Renegociacao paraRenegociacao() {
        return new Renegociacao(id, quantidade,valor,dataDeCriacao);
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

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
