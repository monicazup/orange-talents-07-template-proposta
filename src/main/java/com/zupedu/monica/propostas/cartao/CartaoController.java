package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.api_externa.ContasClient;
import com.zupedu.monica.propostas.api_externa.dto_resultado.ResultadoAvisoViagem;
import com.zupedu.monica.propostas.api_externa.dto_resultado.ResultadoBloqueio;
import com.zupedu.monica.propostas.api_externa.dto_resultado.ResultadoCarteira;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoInclusaoCarteira;
import com.zupedu.monica.propostas.api_externa.dto_solicitacao.SolicitacaoBloqueio;

import com.zupedu.monica.propostas.cartao.dto.AvisoRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    ContasClient apiContas;

    @Deprecated
    public CartaoController(EntityManager manager) {
        this.manager = manager;
    }

    @PersistenceContext
    EntityManager manager;

    @Autowired
    HttpServletRequest request;

    public final Logger logger = LoggerFactory.getLogger(CartaoController.class);

    @Transactional
    @PatchMapping("/{idCartao}/bloqueio")
    public ResponseEntity<ResultadoBloqueio> bloquearCartao(@PathVariable("idCartao") @NotBlank String idCartao) {

        ResultadoBloqueio resultadoBloqueio = new ResultadoBloqueio();

        Cartao cartao = Cartao.procuraCartaoPorId(manager, idCartao);
        /*Verifica se o cartão é inválido ou nulo, retorna 400 BAD REQUEST*/
        if (cartao == null) {
            logger.info("Cartão nulo ou não encontrado.");
            return ResponseEntity.notFound().build();
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
            return ResponseEntity.badRequest().body(resultadoBloqueio);
        }

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        /*Instanciar */
        Bloqueio novoBloqueio = new Bloqueio(userAgent, ipSolicitante, cartao, true);

        SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio("Proposta");

        /* Solicitar bloqueio para Api Accounts via Feign antes de persistir */
        try {
            resultadoBloqueio = apiContas.solicitarBloqueio(solicitacaoBloqueio, idCartao).getBody();
        } catch (FeignException e) {
            logger.info("Feign exception");

            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value() && resultadoBloqueio.getResultadoBloqueioString().equalsIgnoreCase("FALHA")) {
                novoBloqueio.setAtivo(false);
                logger.info("API accounts retornou FALHA.");
            }
        }

        logger.info("Persistindo bloqueio de cartão.");
        novoBloqueio.setSistemaResponsavel(solicitacaoBloqueio.getSistemaResponsavel());

        /*Persistir bloqueio, retornar 200 OK */
        manager.persist(novoBloqueio);
        return ResponseEntity.ok().body(resultadoBloqueio);
    }

    @Transactional
    @PatchMapping("/{idCartao}/aviso-viagem")
    public ResponseEntity<ResultadoAvisoViagem> registrarAvisoViagem(@PathVariable("idCartao") String idCartao, @RequestBody @Valid AvisoRequest avisoRequest) {
        ResultadoAvisoViagem resultado = new ResultadoAvisoViagem();

        Cartao cartao = Cartao.procuraCartaoPorId(manager, idCartao);

        /*Verificar se cartao é valido, retornar 404 */
        if (cartao == null) {
            logger.info("Cartão nulo ou não encontrado.");
            return ResponseEntity.notFound().build();
        }

        try {
            resultado = apiContas.solicitarAvisoViagem(avisoRequest, idCartao).getBody();
        } catch (FeignException e) {
            logger.info("Feign exception");

            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value() && resultado.getResultadoString().equalsIgnoreCase("FALHA")) {
                return ResponseEntity.badRequest().body(resultado);
            }
        }

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");


        Aviso aviso = new Aviso(avisoRequest, cartao, userAgent, ipSolicitante);
        manager.persist(aviso);
        return ResponseEntity.ok().body(resultado);

    }

    @Transactional
    @PatchMapping("/{id}/associar-paypal")
    public ResponseEntity<Carteira> associarAPaypal(@PathVariable("id") String idCartao,
                                                    @Valid @RequestBody SolicitacaoInclusaoCarteira request,

                                                    UriComponentsBuilder uriBuilder) {
        ResultadoCarteira resultado = new ResultadoCarteira();
        Cartao cartao = Cartao.procuraCartaoPorId(manager, idCartao);

        /*Verificar se cartao é valido, retornar 404 */
        if (cartao == null) {
            logger.info("Cartão nulo ou não encontrado.");
            return ResponseEntity.notFound().build();
        }

        try {
            resultado = apiContas.associarACarteira(request, idCartao).getBody();
        } catch (FeignException e) {
            logger.info("Feign exception");

            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value() && resultado.equals("FALHA")) {
                return ResponseEntity.badRequest().build();
            }

        }
        Carteira carteira = new Carteira(request, resultado, cartao);
        manager.persist(carteira);
        URI location = uriBuilder.path("/biometria/{id}")
                .buildAndExpand(carteira.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
