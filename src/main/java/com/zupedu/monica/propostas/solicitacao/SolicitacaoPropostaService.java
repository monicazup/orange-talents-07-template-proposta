package com.zupedu.monica.propostas.solicitacao;

import com.zupedu.monica.propostas.api_externa.CartoesClient;
import com.zupedu.monica.propostas.proposta.Proposta;
import com.zupedu.monica.propostas.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class SolicitacaoPropostaService {

    @Autowired
    private CartoesClient consultaApiCartoes;

    @Autowired
    private PropostaRepository repository;


    @Autowired
    private EntityManager manager;


    @Transactional
    public List<Proposta> listarPropostasEmAnalise() {
        List<Proposta> propostasEmAnalise = repository.findByStatus(StatusSolicitacao.EM_ANALISE);
        return propostasEmAnalise;
    }

    public List<SolicitacaoAnalise> converterPropostaParaSolicitacao(List<Proposta> propostas) {
        List<SolicitacaoAnalise> solicitacoesPendentes = new ArrayList<>();

        for (Proposta proposta : propostas) {
            SolicitacaoAnalise solicitacao = new SolicitacaoAnalise(proposta);
            solicitacoesPendentes.add(solicitacao);
        }
        return solicitacoesPendentes;
    }

    @Transactional
    public void analisarSolicitacoes(List<SolicitacaoAnalise> solicitacoesPendentes) {
        for (SolicitacaoAnalise solicitacao : solicitacoesPendentes) {

            Proposta proposta = manager.find(Proposta.class, solicitacao.getIdProposta());

            try {
                consultaApiCartoes.retornarResultadoSolicitacao(solicitacao);
                proposta.setStatus(StatusSolicitacao.ELEGIVEL);

            } catch (FeignException e) {
                if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value())
                    proposta.setStatus(StatusSolicitacao.NAO_ELEGIVEL);
            }
            manager.merge(proposta);
        }

    }

    @Deprecated
    public SolicitacaoPropostaService() {

    }
}
