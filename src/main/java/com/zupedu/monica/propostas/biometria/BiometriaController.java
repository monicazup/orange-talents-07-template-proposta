package com.zupedu.monica.propostas.biometria;

import com.zupedu.monica.propostas.cartao.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController @RequestMapping("/biometria")
    public class BiometriaController {

        public BiometriaController(EntityManager manager) {
            this.manager = manager;
        }

        EntityManager manager;

        @PostMapping("/{id}")
        @Transactional
        public ResponseEntity<Biometria> cadastrar(@PathVariable("id") String idCartao,
                          @RequestBody @Valid BiometriaRequest request,
                          UriComponentsBuilder uriComponentsBuilder/*,
                           @AuthenticationPrincipal Usuario usuario */){

        Cartao cartao = manager.find(Cartao.class, idCartao);

        if (cartao == null) {
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = new Biometria(request, cartao);

        manager.persist(biometria);


            URI location = uriComponentsBuilder.path("/biometria/{id}")
                    .buildAndExpand(biometria.getId())
                    .toUri();
        return ResponseEntity.created(location).build();
    }

}
