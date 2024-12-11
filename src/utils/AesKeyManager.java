package utils;

import encryption.Aes;

import java.io.*;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesKeyManager {

    private static final String ENV_FILE = ".env";
    private static final String AES_KEY_PROPERTY = "AES_KEY";
    private static final String AES_KEY_BASE64 = "dPqA/MC4Lp9rkX1hpr9ypw==";

    /**
     * Loads or generates an AES key. If a key is defined in the static variable,
     * it is used. Otherwise, a new key is generated.
     * @return The AES key
     */
    public static SecretKey loadOrGenerateKey() throws Exception {
        // Checks if a key is defined in the static variable
        if (AES_KEY_BASE64 != null && !AES_KEY_BASE64.isEmpty()) {
            byte[] decodedKey = Base64.getDecoder().decode(AES_KEY_BASE64);
            return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        }

        // If the key is not defined, generate a new one
        SecretKey secretKey = Aes.generateKey();
        return secretKey;
    }

    /**
     * Loads or generates an AES key. If no key exists in the .env file, a new key is generated.
     * @return The AES key
     */
    public static SecretKey __loadOrGenerateKey() throws Exception {
        File envFile = new File(ENV_FILE);

        if (envFile.exists()) {
            try {
                // Read the key from the .env file
                String keyBase64 = readKeyFromFile(envFile);
                if (keyBase64 == null || keyBase64.isEmpty()) {
                    throw new IOException("AES key is missing or empty in .env file");
                }
                byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
                return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            } catch (Exception e) {
                System.out.println("Invalid or missing key in .env. Generating a new one...");
            }
        }

        // If the file does not exist or the key is missing, generate a new key
        SecretKey secretKey = Aes.generateKey();
        saveKeyToFile(secretKey, envFile);
        return secretKey;
    }

    /**
     * Saves an AES key to a .env file
     * @param secretKey The AES key to save
     * @param file The file where the key should be saved
     */
    private static void saveKeyToFile(SecretKey secretKey, File file) throws IOException {
        String keyBase64 = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(AES_KEY_PROPERTY + "=" + keyBase64 + "\n");
        }
        System.out.println("Key saved successfully in " + file.getAbsolutePath());
    }

    /**
     * Reads the AES key from a .env file
     * @param file The file to read from
     * @return The key encoded in Base64
     */
    private static String readKeyFromFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(AES_KEY_PROPERTY + "=")) {
                    return line.split("=")[1];
                }
            }
            throw new IOException("AES key not found in .env file");
        }
    }
}
