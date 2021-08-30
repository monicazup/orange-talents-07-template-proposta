package com.zupedu.monica.propostas.proposta;

import com.zupedu.monica.propostas.api_externa.dto_solicitacao.StatusSolicitacaoEnum;
import com.zupedu.monica.propostas.cartao.Cartao;

import java.math.BigDecimal;

public class ConsultaDePropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private BigDecimal salario;
    private EnderecoDeSolicitante enderecoDeSolicitante;
    private StatusSolicitacaoEnum status;
    private Cartao cartao;


    public ConsultaDePropostaResponse(Proposta proposta) {

        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.salario = proposta.getSalario();
        this.enderecoDeSolicitante = proposta.getEnderecoDeSolicitante();
        this.status = proposta.getStatus();
        this.cartao = proposta.getCartao();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public EnderecoDeSolicitante getEnderecoDeSolicitante() {
        return enderecoDeSolicitante;
    }

    public StatusSolicitacaoEnum getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
