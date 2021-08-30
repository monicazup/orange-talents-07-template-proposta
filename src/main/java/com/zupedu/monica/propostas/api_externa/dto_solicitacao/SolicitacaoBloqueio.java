package com.zupedu.monica.propostas.api_externa.dto_solicitacao;

public class SolicitacaoBloqueio {
    private String sistemaResponsavel;

    public SolicitacaoBloqueio(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
