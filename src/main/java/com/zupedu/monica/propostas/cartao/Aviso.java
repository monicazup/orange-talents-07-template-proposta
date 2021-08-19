package com.zupedu.monica.propostas.cartao;

import java.time.LocalDate;

public class Aviso {
    private LocalDate validoAte;
    private String destino;

    public Aviso(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }
    public Aviso(AvisoRequest avisoRequest){
        this.validoAte = avisoRequest.getValidoAte();
        this.destino = avisoRequest.getDestino();
    }
}
