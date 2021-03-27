
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class MyCbcClass {

  public String applyCBC(String strKey, String plainText) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
    byte[] bytesIV = "7cVgr5cbdCZVw5WY".getBytes("UTF-8");

    /* KEY + IV setting */
    IvParameterSpec iv = new IvParameterSpec(bytesIV);
    SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes("UTF-8"), "AES");

    /* Ciphering */
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);  // Noncompliant because IV hard coded and cannot vary with each ciphering round
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
    return DatatypeConverter.printBase64Binary(bytesIV) // IV is typically published
            + ";" + DatatypeConverter.printBase64Binary(encryptedBytes);
  }
}