package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.exception.ErroPadronizado;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

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

        /*Verifica se o cartão é inválido ou nulo, retorna 400 BAD REQUEST*/
        if (cartao == null) {
            logger.info("Cartão nulo ou não encontrado.");
            return ResponseEntity.badRequest().build();
        }

        /* Verificar se o usuario é o titular do cartao. Retornar 422 UNPROCESSABLE ENTITY */
//        if(!cartao.getProposta().getEmail().equalsIgnoreCase(userRequest){
//            return ResponseEntity.unprocessableEntity().build();
//        }

        /* Verificar se cartao estiver bloqueado, retornar 400 BAD REQUEST*/
        List<Bloqueio> bloqueiosDoCartao = cartao.getBloqueios();
        for (Bloqueio bloqueio : bloqueiosDoCartao) {
            if (bloqueio.isAtivo())
                logger.info("Tentativa de bloquear um cartão já com bloqueio ativo");
                return ResponseEntity.badRequest().build();
        }


        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        /*Instanciar e persistir bloqueio, retornar 200 OK */
        Bloqueio novoBloqueio = new Bloqueio (userAgent, cartao, true);

        logger.info("Persistindo bloqueio de cartão.");
        manager.persist(novoBloqueio);

        return ResponseEntity.ok().build();
    }


}
