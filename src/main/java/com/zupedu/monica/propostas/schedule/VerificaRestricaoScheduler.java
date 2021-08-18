package com.zupedu.monica.propostas.schedule;

import com.zupedu.monica.propostas.proposta.Proposta;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoAnalise;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static com.zupedu.monica.propostas.solicitacao.StatusSolicitacao.EM_ANALISE;

@Configuration
@EnableScheduling
@EnableAsync
public class VerificaRestricaoScheduler {

    @Autowired
    SolicitacaoPropostaService service;

//    public VerificaRestricaoScheduler(SolicitacaoPropostaService service) {
//        this.service = service;
//    }

    @Scheduled(fixedDelay = 10000)
    public void analisarPropostas() {

        List<Proposta> propostas = service.listarPropostasEmAnalise();

        if (!propostas.isEmpty()) {
            List<SolicitacaoAnalise> solicitacoesPendentes =
                    service.converterPropostaParaSolicitacao(propostas);

        }
    }


}
