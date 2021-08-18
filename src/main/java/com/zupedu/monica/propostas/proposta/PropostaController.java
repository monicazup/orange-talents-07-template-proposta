package com.zupedu.monica.propostas.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {


    EntityManager manager;

    public PropostaController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest request,
                                       UriComponentsBuilder uriBuilder) {

        Proposta proposta = request.toProposta();

        manager.persist(proposta);

        URI location = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
