package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.cartao.dto.AvisoRequest;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
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
