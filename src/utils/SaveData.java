package utils;

import encryption.Aes;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData {

    /**
     * Save the data to a file with no key
     * @param name name of the service related to the password
     * @param password encrypt password to save
     * @param method encryption method used
     */
    public static void saveData(String name, String password, String method, SecretKey aesKey) {
        saveData(name, password, method, "null", aesKey);
    }
    /**
     * Save the data to a file with a key
     * @param key key used for encryption
     */
    public static void saveData(String name, String password, String method, String key, SecretKey aesKey) {
        File file = new File("data.csv");

        // Préparer la ligne à enregistrer
        String dataLine = name + ":" + password + ":" + method + ":" + key;
        try {
            // Chiffrer la ligne avec AES
            String encryptedData = Aes.encrypt(dataLine, aesKey);

            // Écrire la donnée chiffrée dans le fichier
            try (FileWriter writer = new FileWriter(file, true)) {

                // Ajouter la ligne chiffrée au fichier
                writer.write(encryptedData + "\n");
                System.out.println("\u001B[33mData successfully saved!\u001B[0m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
