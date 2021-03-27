package solucion;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class MyCbcClass {

    SecureRandom random = new SecureRandom();

    public String applyCBC(String strKey, String plainText) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] bytesIV = new byte[16];
        random.nextBytes(bytesIV);

        /* KEY + IV setting */
        IvParameterSpec iv = new IvParameterSpec(bytesIV);
        SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes("UTF-8"), "AES");

        /* Ciphering */
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return DatatypeConverter.printBase64Binary(bytesIV)
                + ";" + DatatypeConverter.printBase64Binary(encryptedBytes);
    }
}
