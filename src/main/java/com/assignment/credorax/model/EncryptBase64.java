package com.assignment.credorax.model;

import java.util.Base64;

public class EncryptBase64 {
    public static String encrypt(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String decode(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes);
    }
}
