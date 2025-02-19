package com.zupedu.monica.propostas.cartao.dto;

import com.zupedu.monica.propostas.cartao.Vencimento;

import java.time.LocalDateTime;
import java.time.MonthDay;

public class VencimentoRequest {

    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoRequest(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento paraVencimento() {
        return new Vencimento(id, dia, dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
