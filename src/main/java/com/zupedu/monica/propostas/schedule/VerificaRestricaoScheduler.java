package com.zupedu.monica.propostas.schedule;

import com.zupedu.monica.propostas.proposta.Proposta;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoAnalise;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

import static com.zupedu.monica.propostas.api_externa.dto_solicitacao.StatusSolicitacaoEnum.EM_ANALISE;


@Configuration
@EnableScheduling
@EnableAsync
public class VerificaRestricaoScheduler {

    @Autowired
    SolicitacaoPropostaService service;


    @Scheduled(fixedDelayString = "${periodicidade.verifica-restricao}")
    public void analisarPropostas() {

        List<Proposta> propostasEmAnalise = service.listarPropostasPorStatus(EM_ANALISE);
        List<SolicitacaoAnalise> solicitacoesPendentes = new ArrayList<>();
        if (!propostasEmAnalise.isEmpty()) {
            solicitacoesPendentes = service.converterPropostaParaSolicitacao(propostasEmAnalise);
        }
        service.analisarSolicitacoes(solicitacoesPendentes);

    }

}
