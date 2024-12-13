package menu;

import encryption.Vigenere;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import javax.crypto.SecretKey;
import java.util.Scanner;

public class VigenereMenu {
    /**
     * Display the Vigenere menu
     */
    public static void vigenere() throws Exception {
        System.out.println("--- Vigenere Menu --- \n");
        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        System.out.print("Enter the password: ");
        String password = Common.getAlphabetCharactersOnly();
        // Ask the key to encrypt the password
        System.out.print("Enter the key: ");
        String key = Common.getAlphabetCharactersOnly();

        // Display the service and key
        System.out.println("Encrypting password for service: " + service + " using key: " + key);

        // Encrypt the password
        String encryptedPassword = Vigenere.encrypt(password, key);
        System.out.println("Encrypted password: " + encryptedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted password and the key
        SaveData.saveData(service, encryptedPassword, "VIGENERE", key, aesKey);
    }

}
