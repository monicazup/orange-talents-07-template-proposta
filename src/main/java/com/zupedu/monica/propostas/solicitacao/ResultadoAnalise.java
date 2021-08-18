package com.zupedu.monica.propostas.solicitacao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoAnalise {

    private String documento;
    private String nome;
    @Enumerated(EnumType.STRING)
    private ResultadoSolicitacao resultadoSolicitacao;
    private String idProposta;

}
