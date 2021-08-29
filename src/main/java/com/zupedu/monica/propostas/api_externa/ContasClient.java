package com.zupedu.monica.propostas.api_externa;

import com.zupedu.monica.propostas.cartao.dto.CartaoRequest;
import com.zupedu.monica.propostas.solicitacao.SolicitacaoCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;



@Component
@FeignClient(name = "contas", url = "http://localhost:8888/api/cartoes")
public interface ContasClient {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CartaoRequest> retornaCartao(@RequestBody SolicitacaoCartao solicitacao);
}
