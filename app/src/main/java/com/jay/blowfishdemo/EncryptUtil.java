package com.jay.blowfishdemo;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {
    private static final String ALGORITHM = "Blowfish";
    private static final String TAG = EncryptUtil.class.getSimpleName();

    public static String encrypt(String to_encrypt, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] hasil = cipher.doFinal(to_encrypt.getBytes());
            return Base64.encodeToString(hasil, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String to_decrypt, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] hasil = cipher.doFinal(Base64.decode(to_decrypt, Base64.DEFAULT));
            return new String(hasil);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
