package com.nology.marvelbackend;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public class Auth_Key {

    public String getPublicKey() {
        return "844a9f7fd879379f964c117c04110cb2";
    }

    public String getPrivateKey() {
        return "8f64f31445c6f9115891aa3b87224a7384480e63";
    }

    public long getTimestamp() {
        Date date = new Date();
        return ( new Date().getTime()) / 1000;
    }

    public String getURI () {
        Long ts = getTimestamp();
        return "&ts=" + ts + "&apikey=" + getPublicKey() + "&hash=" + getMd5(ts+getPrivateKey()+getPublicKey());
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
