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

    @Override
    public boolean equals(Object o) {
        if (this.resultado == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoAvisoViagem that = (ResultadoAvisoViagem) o;
        return resultado == that.resultado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultado);
    }

    public ResultadoAvisoViagem() {    }
}
