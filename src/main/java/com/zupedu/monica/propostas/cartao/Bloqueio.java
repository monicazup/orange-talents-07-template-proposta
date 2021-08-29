package com.zupedu.monica.propostas.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    @NotNull
    private boolean ativo = false;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    public Bloqueio(String id, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = LocalDateTime.now();
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(String sistemaResponsavel, Cartao cartao, Boolean ativo) {
        this.ativo = true;
        this.bloqueadoEm = LocalDateTime.now();
        this.sistemaResponsavel = sistemaResponsavel;
        this.cartao = cartao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Deprecated
    public Bloqueio() {   }
}
