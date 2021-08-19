package com.zupedu.monica.propostas.cartao;

import java.time.LocalDateTime;
import java.time.MonthDay;

public class Vencimento {

    private String id;
    private MonthDay dia;
    private LocalDateTime dataDeCriacao;

    public Vencimento(String id, MonthDay dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    @Deprecated
    public Vencimento(){}
}
