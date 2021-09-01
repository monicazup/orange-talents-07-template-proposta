package com.zupedu.monica.propostas.config.security;


import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.util.text.AES256TextEncryptor;

public class Criptografia {

    private static Criptografia instance = null;

    private String textoLimpo;
    private static String secret = "${CRYPTO}";

    public static String criptografar(String textoLimpo) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secret);
        String criptografada = encryptor.encrypt(textoLimpo);
        return criptografada;
    }

    public static String descriptografar(String criptografada) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secret);
        return encryptor.decrypt(criptografada);
    }

    public static String hash(String textoLimpo){
        StringBuilder builder = new StringBuilder();
        return DigestUtils.sha256Hex(builder.append(textoLimpo).append(secret).toString());
    }

}
