package com.zupedu.monica.propostas.api_externa;

import com.zupedu.monica.propostas.solicitacao.ResultadoSolicitacaoAnalise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoAnalise;

@Component
@FeignClient(name = "cartoes", url = "http://localhost:9999/api/solicitacao")

public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET)
    ResultadoSolicitacaoAnalise retornarResultadoSolicitacao(SolicitacaoAnalise solicitacao);

}
