package com.zupedu.monica.propostas.cartao;

import java.time.LocalDateTime;
import java.time.MonthDay;

public class VencimentoRequest {

    private String id;
    private MonthDay dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoRequest(String id, MonthDay dia, LocalDateTime dataDeCriacao) {
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

    public MonthDay getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
