package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.cartao.dto.CarteiraRequest;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Carteira {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira(CarteiraRequest carteiraRequest){
        this.id = carteiraRequest.getId();
        this.email = carteiraRequest.getEmail();
        this.associadaEm = carteiraRequest.getAssociadaEm();
        this.emissor = carteiraRequest.getEmissor();
    }
}
