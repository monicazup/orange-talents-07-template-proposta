package com.zupedu.monica.propostas.api_externa.dto_solicitacao;

import com.zupedu.monica.propostas.api_externa.AnaliseClient;
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

import static com.zupedu.monica.propostas.api_externa.dto_solicitacao.StatusSolicitacaoEnum.*;


@Service
public class SolicitacaoPropostaService {

    @Autowired
    private AnaliseClient consultaApiCartoes;

    @Autowired
    private PropostaRepository repository;


    @Autowired
    private EntityManager manager;


    public List<Proposta> listarPropostasPorStatus(StatusSolicitacaoEnum statusSolicitacao) {
        return repository.findByStatus(statusSolicitacao);
    }

    public List<SolicitacaoAnalise> converterPropostaParaSolicitacao(List<Proposta> propostas) {
        List<SolicitacaoAnalise> solicitacoesPendentes = new ArrayList<>();
        if (!propostas.isEmpty()) {
            for (Proposta proposta : propostas) {
                SolicitacaoAnalise solicitacao = new SolicitacaoAnalise(proposta);
                solicitacoesPendentes.add(solicitacao);
            }
        }
        return solicitacoesPendentes;
    }

    @Transactional
    public void analisarSolicitacoes(List<SolicitacaoAnalise> solicitacoesPendentes) {
        for (SolicitacaoAnalise solicitacao : solicitacoesPendentes) {

            Proposta proposta = manager.find(Proposta.class, solicitacao.getIdProposta());

            try {
                consultaApiCartoes.retornarResultadoSolicitacao(solicitacao);
                proposta.setStatus(ELEGIVEL);

            } catch (FeignException e) {
                if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value())
                    proposta.setStatus(NAO_ELEGIVEL);
            }
            manager.merge(proposta);
        }

    }

    @Deprecated
    public SolicitacaoPropostaService() {
    }
}
