package com.zupedu.monica.propostas.api_externa;

import com.zupedu.monica.propostas.cartao.Cartao;
import com.zupedu.monica.propostas.cartao.dto.CartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.awt.*;

@Component
@FeignClient(name = "contas", url = "http://localhost:8888/api/contas")
public interface ContasClient {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<CartaoRequest> retornaCartao(@RequestParam("idProposta") String idProposta);
}
