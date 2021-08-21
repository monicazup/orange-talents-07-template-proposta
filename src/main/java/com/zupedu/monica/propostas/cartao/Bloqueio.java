package com.zupedu.monica.propostas.cartao;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Bloqueio {
    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    @Deprecated
    public Bloqueio() {   }
}
