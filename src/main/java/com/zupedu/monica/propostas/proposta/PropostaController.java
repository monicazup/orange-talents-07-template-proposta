package com.zupedu.monica.propostas.proposta;

import io.opentracing.Span;
import io.opentracing.Tracer;
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


    @Autowired
    private EntityManager manager;

    @Autowired
    private Tracer tracer;

    @PostMapping @Transactional
    public ResponseEntity<Proposta> cadastrar(@RequestBody @Valid PropostaRequest request,
                                       UriComponentsBuilder uriBuilder) {

        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", "monica.gomides@zup.com");
        activeSpan.log("Testando LOG: Cadastro de proposta");
        Proposta proposta = request.paraProposta();

        manager.persist(proposta);
        tracer.activeSpan().setBaggageItem("proposta.id", proposta.getId().toString());
        URI location = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDePropostaResponse> consultar(@PathVariable("id") Long idProposta) {

        Proposta proposta = manager.find(Proposta.class, idProposta);

        if (proposta==null) {
            return ResponseEntity.notFound().build();
        }

        ConsultaDePropostaResponse propostaResponse = new ConsultaDePropostaResponse(proposta);

        return ResponseEntity.ok().body(propostaResponse);
    }

    @Deprecated
    public PropostaController(){}


}
