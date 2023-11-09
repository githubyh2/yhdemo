package com.yanhao.javaStuday;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Example {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String data = "hello,world";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

        StringBuffer hexBuffer = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hexValue = Integer.toHexString(0xFF & hash[i]);
            if (hexValue.length() == 1) {
                hexBuffer.append('0');
            }
            hexBuffer.append(hexValue);
        }

        System.out.println("加密后的数据是：" + hexBuffer.toString());
        // 77df263f49123356d28a4a8715d25bf5b980beeeb503cab46ea61ac9f3320eda
        // 77df263f49123356d28a4a8715d25bf5b980beeeb503cab46ea61ac9f3320eda

    }
}
