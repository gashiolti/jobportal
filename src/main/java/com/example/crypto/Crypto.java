package com.example.crypto;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Crypto {

    public static String encode(String value) throws UnsupportedEncodingException {
        Base64.Encoder e = Base64.getEncoder();

        return e.encodeToString(value.getBytes("UTF-8"));
    }

    public static String decode(String encrypted) throws UnsupportedEncodingException {
        byte[] b = Base64.getDecoder().decode(encrypted);

        return new String(b, "UTF-8");
    }

//    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(decode("bWljcm9zb2Z0MTIz"));
//    }

}
