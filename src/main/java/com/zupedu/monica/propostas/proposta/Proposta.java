package com.zupedu.monica.propostas.proposta;

import com.zupedu.monica.propostas.config.CPFouCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CPFouCNPJ @NotBlank
    private String documento;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotNull @PositiveOrZero
    private BigDecimal salario;
    @Embedded
    private EnderecoDeSolicitante enderecoDeSolicitante;

    public Proposta(String documento,
                    String email,
                    String nome,
                    BigDecimal salario, EnderecoDeSolicitante enderecoDeSolicitante) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.enderecoDeSolicitante = enderecoDeSolicitante;
    }

    public Long getId() {
        return id;
    }

    @Deprecated
    public Proposta(){}
}
