package encryption;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static utils.Common.bytesToHex;
import static utils.Common.hexToBytes;

public class Aes {

    public static void main(String[] args) throws Exception {
        // Example plaintext
        String plaintext = "Hello, World!";

        // Generate AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Key size: 128, 192, or 256 bits
        SecretKey secretKey = keyGen.generateKey();

        // Encrypt plaintext
        String encryptedText = encrypt(plaintext, secretKey);
        System.out.println("Encrypted: " + encryptedText);

        // Decrypt ciphertext
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted: " + decryptedText);
    }

    /**
     * Encrypt the password using AES
     * @param plaintext the password to encrypt
     * @param secretKey the secret key
     */
    public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {
        System.out.println("AES Encryption");
        Cipher cipher = Cipher.getInstance("AES"); // AES encryption mode
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return bytesToHex(encryptedBytes); // Convert to Hex
    }

    /**
     * Decrypt the password using AES
     * @param ciphertext the encrypted password
     * @param secretKey the secret key
     */
    public static String decrypt(String ciphertext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = hexToBytes(ciphertext); // Convert Hex to Bytes
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}



