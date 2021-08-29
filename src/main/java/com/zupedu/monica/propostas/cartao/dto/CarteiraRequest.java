package com.zupedu.monica.propostas.cartao.dto;

import com.zupedu.monica.propostas.cartao.Carteira;

import java.time.LocalDateTime;

public class CarteiraRequest {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public CarteiraRequest(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    @Deprecated
    public CarteiraRequest(){}

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

    public Carteira paraCarteira() {
        return new Carteira(Long.valueOf(this.id), this.email, this.associadaEm, this.emissor);
    }

}
