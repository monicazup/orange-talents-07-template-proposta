package com.zupedu.monica.propostas.schedule;

import com.zupedu.monica.propostas.api_externa.ContasClient;
import com.zupedu.monica.propostas.cartao.Cartao;
import com.zupedu.monica.propostas.cartao.dto.CartaoRequest;
import com.zupedu.monica.propostas.proposta.Proposta;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoCartao;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoPropostaService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static com.zupedu.monica.propostas.solicitacao.StatusSolicitacao.*;

@Component
public class AssociaCartaoScheduler {

    @Autowired
    private SolicitacaoPropostaService service;

    @Autowired
    private EntityManager manager;

    @Autowired
    ContasClient consultaApiContas;

    @Transactional
    @Scheduled(fixedDelayString = "${periodicidade.associa-cartao}")
    public void associaCartao() {

        List<Proposta> propostasElegiveis = service.listarPropostasPorStatus(ELEGIVEL);

        if (!propostasElegiveis.isEmpty()) {
            for (Proposta proposta : propostasElegiveis) {

                SolicitacaoCartao solicitacao = new SolicitacaoCartao(proposta);
                try {
                    CartaoRequest cartaoRequest = consultaApiContas.retornaCartao(solicitacao).getBody();
                    Cartao cartao = new Cartao(cartaoRequest, proposta);

                    proposta.setCartao(cartao);
                    proposta.setStatus(FINALIZADO);

                    manager.merge(proposta);
                } catch (FeignException e) {
                    System.out.println("Log falso: Feign exception");
                }
            }
        }

    }

}