package com.zupedu.monica.propostas.api_externa.dto_solicitacao;

import com.zupedu.monica.propostas.proposta.Proposta;

public class SolicitacaoCartao {
    private String documento;
    private String nome;
    private String idProposta;

    public SolicitacaoCartao(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }
    @Deprecated
    public SolicitacaoCartao(){}

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}