package com.zupedu.monica.propostas.api_externa;

import com.zupedu.monica.propostas.api_externa.solicitacao.SolicitacaoCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zupedu.monica.propostas.api_externa.solicitacao.SolicitacaoAnalise;

@Component
@FeignClient(name = "analise", url = "http://localhost:9999/api/solicitacao")
public interface AnaliseClient {

    @RequestMapping(method = RequestMethod.GET)
    SolicitacaoCartao retornarResultadoSolicitacao(SolicitacaoAnalise solicitacao);

}
