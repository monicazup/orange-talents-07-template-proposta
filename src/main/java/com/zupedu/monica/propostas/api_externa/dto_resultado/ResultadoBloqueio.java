package com.zupedu.monica.propostas.api_externa.dto_resultado;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoBloqueio {
    @Enumerated(EnumType.STRING)
    ResultadoBloqueioEnum resultadoBloqueio;

    public String getResultadoBloqueioString() {
        return resultadoBloqueio.toString();
    }

    public ResultadoBloqueio(ResultadoBloqueioEnum resultadoBloqueio) {
        this.resultadoBloqueio = resultadoBloqueio;
    }


    public ResultadoBloqueio(){}
}
