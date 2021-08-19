package com.zupedu.monica.propostas.schedule;

import com.zupedu.monica.propostas.proposta.Proposta;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoAnalise;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableScheduling
@EnableAsync
public class VerificaRestricaoScheduler {

    @Autowired
    SolicitacaoPropostaService service;


    @Scheduled(fixedDelay = 10000)
    public void analisarPropostas() {

        List<Proposta> propostasEmAnalise = service.listarPropostasEmAnalise();
        List<SolicitacaoAnalise> solicitacoesPendentes = new ArrayList<>();
        if (!propostasEmAnalise.isEmpty()) {
            solicitacoesPendentes = service.converterPropostaParaSolicitacao(propostasEmAnalise);
        }
        service.analisarSolicitacoes(solicitacoesPendentes);

    }

}
