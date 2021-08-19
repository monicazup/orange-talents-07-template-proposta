package com.zupedu.monica.propostas.cartao;

import java.time.LocalDate;

public class AvisoRequest {
    private LocalDate validoAte;
    private String destino;

    public AvisoRequest(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
