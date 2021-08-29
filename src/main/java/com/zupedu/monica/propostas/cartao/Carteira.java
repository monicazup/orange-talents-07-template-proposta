package com.zupedu.monica.propostas.cartao;

import com.zupedu.monica.propostas.cartao.dto.CarteiraRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;
    @ManyToOne
    Cartao cartao;

    public Carteira(Long id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

}
