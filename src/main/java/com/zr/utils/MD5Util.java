package com.zr.utils;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author zr
 * @date 2023/4/25 16:43
 */public class MD5Util {

        public static String md5(String pass) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(pass.getBytes(StandardCharsets.UTF_8));
                BigInteger number = new BigInteger(1, messageDigest);
                StringBuilder md5 = new StringBuilder(number.toString(16));
                while (md5.length() < 32) {
                    md5.insert(0, "0");
                }
                return md5.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return pass;
            }
        }
}

