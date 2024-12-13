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
        Common.clearScreen();

        // Print the title and menu header
        Common.printTitle("AES MENU", "Welcome to the AES encryption Menu!");

        // Ask the name of the service
        String service = Common.getServiceName();

        // Ask the password to encrypt
        String password = getPassword();

        // Generate the secret key
        SecretKey secretKey = Aes.generateKey();

        // Display the service being encrypted
        System.out.println("\u001B[34mEncrypting password for service: \u001B[0m" + service);

        // Encrypt the password
        String encryptedPassword = Aes.encrypt(password, secretKey);
        System.out.println("\u001B[34mEncrypted password: \u001B[0m" + encryptedPassword);

        // Retrieve or generate the AES key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Encode the secret key for saving
        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // Save the encrypted password and the encoded secret key
        SaveData.saveData(service, encryptedPassword, "AES", encodedSecretKey, aesKey);
    }

    /**
     * Get the password from the user
     *
     * @return the password
     */
    private static String getPassword() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\u001B[36mEnter the password: \u001B[0m"); // User input prompt
            String password = scanner.nextLine();

            // Validate the password
            if (!password.isEmpty()) {
                return password;
            } else {
                System.out.println("\u001B[31mPlease enter a valid password.\u001B[0m"); // Error message
            }
        }
    }
}