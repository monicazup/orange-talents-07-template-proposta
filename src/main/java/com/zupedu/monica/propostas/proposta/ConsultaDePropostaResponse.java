package com.zupedu.monica.propostas.proposta;

import com.zupedu.monica.propostas.cartao.Cartao;
import com.zupedu.monica.propostas.config.CPFouCNPJ;
import com.zupedu.monica.propostas.solicitacao.StatusSolicitacao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ConsultaDePropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private BigDecimal salario;
    private EnderecoDeSolicitante enderecoDeSolicitante;
    private StatusSolicitacao status;
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

    public StatusSolicitacao getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
