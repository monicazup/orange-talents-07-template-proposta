package com.zupedu.monica.propostas.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {
    @Id
    private Long id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueio> bloqueios;
    private List<Aviso> avisos;
    private List<Carteira> carteiras;
    private List<Parcela> parcelas;
    private BigDecimal limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    public Cartao(Long id,
                  LocalDateTime emitidoEm,
                  String titular,
                  List<Bloqueio> bloqueios,
                  List<Aviso> avisos,
                  List<Carteira> carteiras,
                  List<Parcela> parcelas,
                  BigDecimal limite,
                  Renegociacao renegociacao,
                  Vencimento vencimento,
                  String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    @Deprecated
    public Cartao(){  }
}
