package com.zupedu.monica.propostas.api_externa;

import com.zupedu.monica.propostas.cartao.Cartao;
import com.zupedu.monica.propostas.cartao.CartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "contas", url = "http://localhost:8888/api/contas")
public interface ContasClient {

    @RequestMapping(method = RequestMethod.GET)
    Cartao retornaCartao(CartaoRequest cartaoRequest);
}
