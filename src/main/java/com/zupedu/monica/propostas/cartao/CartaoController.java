package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.api_externa.ContasClient;
import com.zupedu.monica.propostas.api_externa.solicitacao.ResultadoBloqueio;
import com.zupedu.monica.propostas.api_externa.solicitacao.SolicitacaoBloqueio;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    ContasClient apiContas;

    public CartaoController(EntityManager manager) {
        this.manager = manager;
    }

    @PersistenceContext
    EntityManager manager;


    @Autowired
    HttpServletRequest request;

    public final Logger logger = LoggerFactory.getLogger(CartaoController.class);

    @Transactional
    @PutMapping("/{idCartao}/bloqueio")
    public ResponseEntity<Bloqueio> bloquearCartao(@PathVariable("idCartao") @NotBlank String idCartao) {


        Cartao cartao = manager.find(Cartao.class, idCartao);
        ResultadoBloqueio resultadoBloqueio = new ResultadoBloqueio();

        /*Verifica se o cartão é inválido ou nulo, retorna 400 BAD REQUEST*/
        if (cartao == null) {
            logger.info("Cartão nulo ou não encontrado.");
            return ResponseEntity.badRequest().build();
        }

        /* Verificar se o usuario é o titular do cartao. Retornar 422 UNPROCESSABLE ENTITY */
        /* if(!cartao.getProposta().getEmail().equalsIgnoreCase(userRequest){
            return ResponseEntity.unprocessableEntity().build();
        }*/

        /* Verificar se cartao tem bloqueio ativo. retornar 400 BAD REQUEST*/
        List<Bloqueio> bloqueiosDoCartao = cartao.getBloqueios();
        for (Bloqueio bloqueio : bloqueiosDoCartao) {
            if (bloqueio.isAtivo())
                logger.info("Tentativa de bloquear um cartão já com bloqueio ativo");
            return ResponseEntity.badRequest().build();
        }

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        /*Instanciar */
        Bloqueio novoBloqueio = new Bloqueio(userAgent, cartao, true);

        /* Solicitar bloqueio para Api Accounts via Feign antes de persistir */
        SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio("Proposta");
        try {
            resultadoBloqueio = apiContas.solicitarBloqueio(solicitacaoBloqueio, idCartao).getBody();
        } catch (FeignException e) {
            logger.info("Feign exception");

            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                novoBloqueio.setAtivo(false);
                logger.info("API accounts retornou FALHA.");
            }
        }

        logger.info("Persistindo bloqueio de cartão.");
        novoBloqueio.setSistemaResponsavel(solicitacaoBloqueio.getSistemaResponsavel());

        /*Persistir bloqueio, retornar 200 OK */
        manager.persist(novoBloqueio);
        return ResponseEntity.ok().build();
    }

}
