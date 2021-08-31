package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.api_externa.dto_resultado.ResultadoCarteira;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoInclusaoCarteira;
import com.zupedu.monica.propostas.cartao.dto.CarteiraRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    @Column(unique = true)
    private String emissor;
    @Enumerated(EnumType.STRING)
    private CarteirasEnum carteirasEnum;
    @ManyToOne(cascade = CascadeType.MERGE)
    Cartao cartao;

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira(CarteirasEnum tipoCarteira,
                    SolicitacaoInclusaoCarteira solicitacao,
                    ResultadoCarteira resultado,
                    Cartao cartao) {
        this.carteirasEnum = tipoCarteira;
        this.id = resultado.getId();
        this.emissor = solicitacao.getCarteira();
        this.associadaEm = LocalDateTime.now();
        this.email = solicitacao.getEmail();
        this.cartao = cartao;

    }

    public String getId() {
        return id;
    }
}
