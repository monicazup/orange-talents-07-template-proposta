package com.zupedu.monica.propostas.proposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

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

    public EnderecoDeSolicitante toEndereco() {
        return new EnderecoDeSolicitante(cep, logradouro, numero, complemento, cidade, estado);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
