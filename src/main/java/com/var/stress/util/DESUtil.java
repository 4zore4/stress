package com.var.stress.util;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

public class DESUtil {

    private final static String IV_PARAMETER = "a1b2c3d4";

    private static final String ALGORITHM = "DES";

    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    private static final String CHARSET = "utf-8";

    private static final String ADD_STRING = "_my_key";

    private static Key generateKey(String password) throws Exception {
        DESKeySpec dks = new DESKeySpec(password.getBytes(CHARSET));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    public static String encrypt(String data,String password) {
        String newData = data + ADD_STRING;
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(newData.getBytes(CHARSET));

            //JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
            //Android平台可以使用android.util.Base64
            return new String(Base64.getEncoder().encode(bytes));

        } catch (Exception e) {
            e.printStackTrace();
            return newData;
        }
    }

    public static String decrypt(String data,String password) {
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    public static void main(String[] args) {
        String newData = encrypt("q","12345678");
        System.out.println(newData);
        String deData = decrypt(newData,"12345678");
        String[] strings = deData.split("_my_key");
        System.out.println(strings[0]);
    }

}
