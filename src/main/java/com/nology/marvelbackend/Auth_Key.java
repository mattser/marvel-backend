package com.nology.marvelbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

@Component
public class Auth_Key {

    @Value("${key.marvel.public}")
    private String marvelPublicKey;

    @Value("${key.marvel.private}")
    private String marvelPrivateKey;

    @Value("${key.google}")
    private String googleKey;

    public String getPublicKey() {
        return marvelPublicKey;
    }

    public String getPrivateKey() {
        return marvelPrivateKey;
    }

    public String getGoogleKey() {
        return googleKey;
    }

    public long getTimestamp() {
        Date date = new Date();
        return ( new Date().getTime()) / 1000;
    }

    public String getURI () {
        System.out.println(marvelPrivateKey);
        System.out.println(marvelPublicKey);
        System.out.println(googleKey);
        Long ts = getTimestamp();
        return "ts=" + ts + "&apikey=" + getPublicKey() + "&hash=" + getMd5(ts+getPrivateKey()+getPublicKey());
    }

    private String getMd5 (String input) {
//        https://www.geeksforgeeks.org/md5-hash-in-java/
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger bigInteger = new BigInteger(1,messageDigest);

            String hash = bigInteger.toString(16);

            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
