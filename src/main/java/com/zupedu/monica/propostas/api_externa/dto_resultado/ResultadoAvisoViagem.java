package com.zupedu.monica.propostas.api_externa.dto_resultado;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

public class ResultadoAvisoViagem {

    @Enumerated(EnumType.STRING)
    ResultadoAvisoViagemEnum resultado;

    public ResultadoAvisoViagemEnum getResultado() {
        return resultado;
    }


    public String getResultadoString() {
        return resultado.toString();
    }
    public ResultadoAvisoViagem() {    }
}
