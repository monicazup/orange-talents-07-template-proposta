package com.zupedu.monica.propostas.proposta;

import com.zupedu.monica.propostas.config.CPFouCNPJ;
import com.zupedu.monica.propostas.config.CampoUnico;
import com.zupedu.monica.propostas.config.security.Criptografia;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class PropostaRequest {

    @CPFouCNPJ
    @NotBlank
    private String documento;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotNull @Valid
    private EnderecoRequest endereco;
    @NotNull @PositiveOrZero
    private BigDecimal salario;
    @CampoUnico(fieldName = "documentoHash", entityClass = Proposta.class)
    private String documentoHash = Criptografia.hash(documento);

    public Proposta paraProposta() {
        return new Proposta(documento, Criptografia.hash(documento),
                email, nome, salario, endereco.toEndereco());
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
