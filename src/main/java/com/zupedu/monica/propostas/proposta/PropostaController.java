package com.zupedu.monica.propostas.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Proposta> cadastrar(@RequestBody @Valid PropostaRequest request,
                                       UriComponentsBuilder uriBuilder) {


        Proposta proposta = request.paraProposta();

        manager.persist(proposta);

        URI location = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDePropostaResponse> consultar(@PathVariable("id") Long idProposta/*,
     @AuthenticationPrincipal Usuario usuario */) {

        Proposta proposta = manager.find(Proposta.class, idProposta);

        if (proposta==null) {
            return ResponseEntity.notFound().build();
        }

        ConsultaDePropostaResponse propostaResponse = new ConsultaDePropostaResponse(proposta);

        return ResponseEntity.ok().body(propostaResponse);
    }
}
