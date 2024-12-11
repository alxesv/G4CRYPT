package encryption;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static utils.Common.bytesToHex;
import static utils.Common.hexToBytes;

public class Aes {
    /**
     * Generate a secret key
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Key size: 128, 192, or 256 bits
        return keyGen.generateKey();
    }

    /**
     * Encrypt the password using AES
     * @param password the password to encrypt
     * @param secretKey the secret key
     */
    public static String encrypt(String password, SecretKey secretKey) throws Exception {
        System.out.println("AES Encryption");
        Cipher cipher = Cipher.getInstance("AES"); // AES encryption mode
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return bytesToHex(encryptedBytes); // Convert to Hex
    }

    /**
     * Decrypt the password using AES
     * @param password the encrypted password
     * @param secretKey the secret key
     */
    public static String decrypt(String password, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = hexToBytes(password); // Convert Hex to Bytes
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}



