package encryption;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static utils.Common.bytesToHex;
import static utils.Common.hexToBytes;

public class Aes {
    /**
     * Generate a secret key using standard Java libraries
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        // 128 bit key, can also be 192 or 256
        keyGen.init(128);
        return keyGen.generateKey();
    }

    /**
     * Encrypt the password using AES
     * @param password the password to encrypt
     * @param secretKey the secret key
     */
    public static String encrypt(String password, SecretKey secretKey) throws Exception {
        System.out.println("AES Encryption");
        // AES encryption mode
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        // Convert to Hex
        return bytesToHex(encryptedBytes);
    }

    /**
     * Decrypt the password using AES
     * @param password the encrypted password
     * @param secretKey the secret key
     */
    public static String decrypt(String password, SecretKey secretKey) throws Exception {
        System.out.println("AES Decryption");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // Convert Hex to Bytes
        byte[] encryptedBytes = hexToBytes(password);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}



