package com.jay.blowfishdemo;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {


    public static String encrypt(String to_encrypt, String key) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] hasil = cipher.doFinal(to_encrypt.getBytes());
            return Base64.encodeToString(hasil, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String to_decrypt, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//            byte[] hasil = cipher.doFinal(to_decrypt.getBytes());
            byte[] hasil = cipher.doFinal(Base64.decode(to_decrypt, Base64.DEFAULT));
            return new String(hasil);
//            return Base64.encodeToString(hasil, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
            return null;
        }
    }
}
