package com.zupedu.monica.propostas.api_externa;

import com.zupedu.monica.propostas.api_externa.dto_resultado.ResultadoAvisoViagem;
import com.zupedu.monica.propostas.api_externa.dto_resultado.ResultadoBloqueio;
import com.zupedu.monica.propostas.api_externa.dto_resultado.ResultadoCarteira;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoBloqueio;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoInclusaoCarteira;
import com.zupedu.monica.propostas.cartao.dto.AvisoRequest;
import com.zupedu.monica.propostas.cartao.dto.CartaoRequest;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoCartao;
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
    ResponseEntity<ResultadoBloqueio> solicitarBloqueio(@RequestBody SolicitacaoBloqueio solicitacao, @PathVariable("id") String idCartao);

    @PostMapping(value = "/{id}/avisos", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResultadoAvisoViagem> solicitarAvisoViagem(@RequestBody AvisoRequest solicitacao, @PathVariable("id") String idCartao);

    @PostMapping(value = "/{id}/carteiras", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResultadoCarteira> associarACarteira(@RequestBody SolicitacaoInclusaoCarteira request, @PathVariable("id") String idCartao);
}
