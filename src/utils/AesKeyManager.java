package utils;

import encryption.Aes;

import java.io.*;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesKeyManager {

    private static final String ENV_FILE = ".env";
    private static final String AES_KEY_PROPERTY = "AES_KEY";

    /**
     * Charge ou génère une clé AES. Si aucune clé n'existe dans le fichier .env, une nouvelle est générée.
     * @return La clé AES
     */
    public static SecretKey loadOrGenerateKey() throws Exception {
        File envFile = new File(ENV_FILE);

        if (envFile.exists()) {
            try {
                // Lire la clé depuis le fichier .env
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

        // Si le fichier n'existe pas ou la clé est absente, générer une nouvelle clé
        SecretKey secretKey = Aes.generateKey();
        saveKeyToFile(secretKey, envFile);
        return secretKey;
    }

    /**
     * Sauvegarde une clé AES dans un fichier .env
     * @param secretKey La clé AES à sauvegarder
     * @param file Le fichier où sauvegarder la clé
     */
    private static void saveKeyToFile(SecretKey secretKey, File file) throws IOException {
        String keyBase64 = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(AES_KEY_PROPERTY + "=" + keyBase64 + "\n");
        }
        System.out.println("Key saved successfully in " + file.getAbsolutePath());
    }

    /**
     * Lit la clé AES d'un fichier .env
     * @param file Le fichier à lire
     * @return La clé encodée en Base64
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
