package com.rainesinc.warhammer.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

public class Encryption {

    public String encrypt(String plainText){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());
        encryptor.setPoolSize(4);
        encryptor.setPassword(System.getenv("JASYPT_ENCRYPTOR_PASSWORD"));
        return encryptor.encrypt(plainText);
    }

    public static void main(String[] args){
        Encryption encryption = new Encryption();
        System.out.println(encryption.encrypt("$urf0n$n0w"));
    }
}
