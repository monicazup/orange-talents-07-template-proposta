package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.cartao.dto.*;
import com.zupedu.monica.propostas.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cartao {
    @Id
    @Column(insertable = true, updatable = true)
    private Long idCartao;
    private LocalDateTime emitidoEm;
    private String titular;
    @Embedded
    private List<Bloqueio> bloqueios;
    @Embedded
    private List<Aviso> avisos;
    @Embedded
    private List<Carteira> carteiras;
    @Embedded
    private List<Parcela> parcelas;
    private BigDecimal limite;
    @Embedded
    private Renegociacao renegociacao;
    @Embedded
    private Vencimento vencimento;
    @OneToOne
    private Proposta proposta;

    public Cartao(Long idCartao,
                  LocalDateTime emitidoEm,
                  String titular,
                  List<Bloqueio> bloqueios,
                  List<Aviso> avisos,
                  List<Carteira> carteiras,
                  List<Parcela> parcelas,
                  BigDecimal limite,
                  Renegociacao renegociacao,
                  Vencimento vencimento,
                  Proposta proposta) {
        this.idCartao = idCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public Cartao(CartaoRequest request, EntityManager manager) {
        this.idCartao = Long.valueOf(request.getId());
        this.emitidoEm = request.getEmitidoEm();
        this.titular = request.getTitular();
        this.bloqueios = request.getBloqueios()
                .stream()
                .map(BloqueioRequest::paraBloqueio)
                .collect(Collectors.toList());
        ;
        this.avisos = request.getAvisos()
                .stream().map(AvisoRequest::paraAviso).collect(Collectors.toList());
        this.carteiras = request.getCarteiras().stream().map(CarteiraRequest::paraCarteira).collect(Collectors.toList());
        this.parcelas = request.getParcelas().stream().map(ParcelaRequest::paraParcela).collect(Collectors.toList());
        this.limite = BigDecimal.valueOf(request.getLimite());
        this.renegociacao = request.getRenegociacao().paraRenegociacao();
        this.vencimento = request.getVencimento().paraVencimento();
        this.proposta = manager.find(Proposta.class, request.getIdProposta());
    }

    @Deprecated
    public Cartao() {
    }
}
