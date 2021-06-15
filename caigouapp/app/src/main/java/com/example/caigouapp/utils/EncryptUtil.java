package com.example.caigouapp.utils;

import java.security.MessageDigest;

public class EncryptUtil {

    public static String md5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            int i = (b & 0xFF);
            if (i < 0x10) hex.append('0');
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }

}
