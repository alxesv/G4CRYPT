package menu;

import encryption.Aes;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class AesMenu {
    /**
     * Display the AES menu
     */
    public static void aes() throws Exception {
        System.out.println("--- AES Encryption ---\n");

        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        String password = getPassword();
        // Generate the secret key
        SecretKey secretKey = Aes.generateKey();

        // Display the service
        System.out.println("Encrypting password for service: " + service);

        // Encrypt the password
        String encryptedPassword = Aes.encrypt(password, secretKey);
        System.out.println("Encrypted password: " + encryptedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // Save the encrypted password and rotor positions
        SaveData.saveData(service, encryptedPassword, "AES", encodedSecretKey, aesKey);
    }

    /**
     * Get the password from the user
     * @return the password
     */
    private static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the password
        while (true) {
            System.out.print("Enter the password: ");
            String password = scanner.nextLine();
            // Validate the password
            if (!password.isEmpty()) {
                return password;
            } else {
                System.out.println("Please enter a valid password.");
            }
        }
    }
}
