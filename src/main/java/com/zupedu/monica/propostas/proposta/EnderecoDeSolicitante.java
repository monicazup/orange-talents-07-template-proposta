package com.zupedu.monica.propostas.proposta;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Embeddable
public class EnderecoDeSolicitante {

    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    private String cep;
    @NotBlank
    private String cidade;
    private String estado;

    public EnderecoDeSolicitante(String cep, String logradouro, String numero, String complemento, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
    }

    @Deprecated
    public EnderecoDeSolicitante(){

    }
}
