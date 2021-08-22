package com.zupedu.monica.propostas.biometria;

import com.zupedu.monica.propostas.cartao.Cartao;
import com.zupedu.monica.propostas.biometria.BiometriaRequest;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String fingerprintEncodada;
    @OneToOne
    @NotNull
    Cartao cartao;

    public Biometria(BiometriaRequest request, Cartao cartao) {
        this.fingerprintEncodada = request.hash();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
