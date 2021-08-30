package com.zupedu.monica.propostas.config.security;

import java.util.Base64;

public class AutorizacaoViaEmailDoJwt {

    public static String recuperaEmailDoJwt(String bearerToken){
        String token = bearerToken.substring(7, bearerToken.length());
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        int index = payload.indexOf("\"email\":\"");
        String email = payload.substring(index + 9, payload.length() - 2);

        return email;
    }

}
