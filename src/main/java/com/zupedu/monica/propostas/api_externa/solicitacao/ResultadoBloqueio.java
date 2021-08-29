package com.zupedu.monica.propostas.api_externa.solicitacao;

import com.zupedu.monica.propostas.cartao.ResultadoBloqueioEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoBloqueio {
    @Enumerated(EnumType.STRING)
    ResultadoBloqueioEnum resultadoBloqueio;

    public ResultadoBloqueioEnum getResultadoBloqueio() {
        return resultadoBloqueio;
    }

    public ResultadoBloqueio(ResultadoBloqueioEnum resultadoBloqueio) {
        this.resultadoBloqueio = resultadoBloqueio;
    }

    public void setResultadoBloqueio(ResultadoBloqueioEnum resultadoBloqueio) {
        this.resultadoBloqueio = resultadoBloqueio;
    }

    public ResultadoBloqueio(){}
}
