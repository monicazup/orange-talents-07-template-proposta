package com.zupedu.monica.propostas.schedule;

import com.zupedu.monica.propostas.api_externa.ContasClient;
import com.zupedu.monica.propostas.cartao.Cartao;
import com.zupedu.monica.propostas.cartao.dto.CartaoRequest;
import com.zupedu.monica.propostas.proposta.Proposta;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoCartao;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoPropostaService;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static com.zupedu.monica.propostas.api_externa.dto_solicitacao.StatusSolicitacaoEnum.*;

@Component
public class AssociaCartaoScheduler {

    @Autowired
    private SolicitacaoPropostaService service;

    @Autowired
    private EntityManager manager;

    @Autowired
    ContasClient consultaApiContas;

    @Autowired
    Tracer tracer;

    @Transactional
    @Scheduled(fixedDelayString = "${periodicidade.associa-cartao}")
    public void associaCartao() {
        Span activeSpan = tracer.activeSpan();

        List<Proposta> propostasElegiveis = service.listarPropostasPorStatus(ELEGIVEL);

        if (!propostasElegiveis.isEmpty()) {
            for (Proposta proposta : propostasElegiveis) {

                SolicitacaoCartao solicitacao = new SolicitacaoCartao(proposta);
                try {
                    CartaoRequest cartaoRequest = consultaApiContas.retornarCartao(solicitacao).getBody();
                    activeSpan.log("LOG: Consultando API CARTOES para retornar cartão");

                    Cartao cartao = new Cartao(cartaoRequest, proposta);
                    tracer.activeSpan().setBaggageItem("cartao.id_cartao", cartao.getIdCartao());
                    proposta.setCartao(cartao);
                    proposta.setStatus(FINALIZADO);

                    manager.merge(proposta);
                    activeSpan.log("LOG: Atualizando proposta");

                } catch (FeignException e) {
                    System.out.println("Log falso: Feign exception");
                }
            }
        }

    }

}