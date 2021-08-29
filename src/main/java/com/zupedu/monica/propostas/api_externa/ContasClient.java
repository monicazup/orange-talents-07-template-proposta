package com.zupedu.monica.propostas.api_externa;

import com.zupedu.monica.propostas.api_externa.solicitacao.ResultadoBloqueio;
import com.zupedu.monica.propostas.api_externa.solicitacao.SolicitacaoBloqueio;
import com.zupedu.monica.propostas.cartao.dto.CartaoRequest;
import com.zupedu.monica.propostas.api_externa.solicitacao.SolicitacaoCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;



@Component
@FeignClient(name = "contas", url = "http://localhost:8888/api/cartoes")
public interface ContasClient {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CartaoRequest> retornarCartao(@RequestBody SolicitacaoCartao solicitacao);

    @PostMapping(value = "/{id}/bloqueios", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResultadoBloqueio> solicitarBloqueio(@RequestBody SolicitacaoBloqueio solicitacaoBloqueio, @PathVariable("id") String idCartao);
}
