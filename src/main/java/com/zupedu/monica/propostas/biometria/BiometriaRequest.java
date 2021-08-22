package com.zupedu.monica.propostas.biometria;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.zupedu.monica.propostas.cartao.Cartao;
import org.springframework.util.Base64Utils;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {
    @NotBlank
    String fingerprint;

    public String hash(){
        return Base64.getEncoder().encodeToString(this.fingerprint.getBytes());
    }


    public String getFingerprint() {
        return fingerprint;
    }
}
