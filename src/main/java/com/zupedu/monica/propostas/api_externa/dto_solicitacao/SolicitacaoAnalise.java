package com.zupedu.monica.propostas.api_externa.dto_solicitacao;

import com.zupedu.monica.propostas.proposta.Proposta;

public class SolicitacaoAnalise {

    private String documento;
    private String nome;
    private String idProposta;

    public SolicitacaoAnalise(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public SolicitacaoAnalise(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public Long getIdProposta() {
        return Long.valueOf(idProposta);
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }
}
