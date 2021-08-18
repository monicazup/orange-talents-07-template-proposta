package com.zupedu.monica.propostas.solicitacao;

import com.zupedu.monica.propostas.api_externa.CartoesClient;
import com.zupedu.monica.propostas.proposta.Proposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.zupedu.monica.propostas.solicitacao.StatusSolicitacao.EM_ANALISE;

@Service
public class SolicitacaoPropostaService {

    /*Injeção por construtor */
    private CartoesClient consultaApiCartoes;
    public SolicitacaoPropostaService(CartoesClient consultaApiCartoes) {
        this.consultaApiCartoes = consultaApiCartoes;
    }

    @Autowired
    private EntityManager manager;
//    public SolicitacaoPropostaService(EntityManager manager) {
//        this.manager = manager;
//    }

    @Transactional
    public List<Proposta> listarPropostasEmAnalise() {
        Query query = manager
                .createNativeQuery("SELECT id FROM " + Proposta.class.getName() + " WHERE status = :statusAnalise", Proposta.class);
        query.setParameter("statusAnalise", EM_ANALISE);
        List<Proposta> resultados = (List<Proposta>) query.getResultList();
        System.out.println(resultados.size());
        return resultados;
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
                ResultadoSolicitacao resultadoSolicitacao = consultaApiCartoes.retornarResultadoSolicitacao(solicitacao);
                proposta.setStatus(StatusSolicitacao.ELEGIVEL);

            } catch (FeignException e) {
                if(e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value())
                proposta.setStatus(StatusSolicitacao.NAO_ELEGIVEL);

            }
            manager.merge(proposta);
        }

    }

    public SolicitacaoPropostaService() {

    }
}
