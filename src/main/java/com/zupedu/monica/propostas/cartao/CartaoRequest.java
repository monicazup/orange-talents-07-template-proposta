package com.zupedu.monica.propostas.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartaoRequest {

    private Long id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioRequest> bloqueiosRequest;
    private List<AvisoRequest> avisosRequest;
    private List<CarteiraRequest> carteirasRequest;
    private List<ParcelaRequest> parcelasRequest;
    private BigDecimal limite;
    private RenegociacaoRequest renegociacaoRequest;
    private VencimentoRequest vencimentoRequest;
    private String idProposta;


    public CartaoRequest(String id,
                         LocalDateTime emitidoEm,
                         String titular,
                         List<BloqueioRequest> bloqueiosRequest,
                         List<AvisoRequest> avisosRequest,
                         List<CarteiraRequest> carteirasRequest,
                         List<ParcelaRequest> parcelasRequest,
                         BigDecimal limite,
                         RenegociacaoRequest renegociacaoRequest,
                         VencimentoRequest vencimentoRequest,
                         String idProposta) {
        Long.valueOf(id);
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueiosRequest = bloqueiosRequest;
        this.avisosRequest = avisosRequest;
        this.carteirasRequest = carteirasRequest;
        this.parcelasRequest = parcelasRequest;
        this.limite = limite;
        this.renegociacaoRequest = renegociacaoRequest;
        this.vencimentoRequest = vencimentoRequest;
        this.idProposta = idProposta;
    }

    public Cartao paraCartao() {


        List<Bloqueio> bloqueios = new ArrayList<>();
        for (BloqueioRequest bloqueioRequest : bloqueiosRequest) {
            bloqueios.add(bloqueioRequest.paraBloqueio());
        }

        List<Aviso> avisos = new ArrayList<>();
        for (AvisoRequest avisoRequest : avisosRequest) {
            avisos.add(new Aviso(avisoRequest));
        }

        List<Carteira> carteiras = new ArrayList<>();
        for (CarteiraRequest carteiraRequest : carteirasRequest) {
            carteiras.add(new Carteira(carteiraRequest));
        }

        List<Parcela> parcelas = new ArrayList<>();
        for (ParcelaRequest parcelaRequest : parcelasRequest) {
            parcelas.add(new Parcela(parcelaRequest));
        }

        return new Cartao(
                this.id,
                this.emitidoEm,
                this.titular,
                bloqueios,
                avisos,
                carteiras,
                parcelas,
                this.limite,
                renegociacaoRequest.paraRenegociacao(),
                vencimentoRequest.paraVencimento(),
                this.idProposta);
    }

    @Deprecated
    public CartaoRequest() { }

    public Long getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<CarteiraRequest> getCarteirasRequest() {
        return carteirasRequest;
    }

    public List<ParcelaRequest> getParcelasRequest() {
        return parcelasRequest;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public RenegociacaoRequest getRenegociacao() {
        return renegociacaoRequest;
    }

    public VencimentoRequest getVencimento() {
        return vencimentoRequest;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
