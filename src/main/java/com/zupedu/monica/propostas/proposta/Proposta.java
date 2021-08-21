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

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CPFouCNPJ @NotBlank @Column(unique = true)
    private String documento;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotNull @PositiveOrZero
    private BigDecimal salario;
    @Embedded
    private EnderecoDeSolicitante enderecoDeSolicitante;
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;
    @OneToOne(mappedBy = "proposta")
    private Cartao cartao;

    public Proposta(String documento,
                    String email,
                    String nome,
                    BigDecimal salario, EnderecoDeSolicitante enderecoDeSolicitante
                    ) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.enderecoDeSolicitante = enderecoDeSolicitante;
        this.status = StatusSolicitacao.EM_ANALISE;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    @Deprecated
    public Proposta(){}

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
