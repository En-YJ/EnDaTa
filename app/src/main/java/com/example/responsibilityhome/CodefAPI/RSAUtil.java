package com.example.responsibilityhome.CodefAPI;

import android.annotation.TargetApi;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA 암호화 유틸입니다.
 * 키사이즈는 Default로 2048을 사용하고 있습니다.
 * 유틸에서 생성된 키는 각각 인코딩된 String값으로 반환되어 사용됩니다.
 * 사용될 때는 각 키를 디코딩하여 각 키 인스턴스를 생성 후 암호화 또는 복호화에 사용됩니다.
 *
 * @author choedongcheol
 *
 */
public class RSAUtil {
    private static String ENCRYPT_TYPE_RSA = "RSA";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encryptRSA(String plainText, String base64PublicKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] bytePublicKey = Base64.getDecoder().decode(base64PublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_TYPE_RSA);
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(bytePublicKey));

        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytePlain = cipher.doFinal(plainText.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(bytePlain);

        return encrypted;
    }

}