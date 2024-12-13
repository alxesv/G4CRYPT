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
        Common.printTitle("VIGENERE MENU", "Welcome to the VIGENERE encryption Menu!");

        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        String password = getPassword();
        // Ask the key to encrypt the password
        String key = getVigenereKey();

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

    /**
     * Get the Vigenere key from the user
     * @return the Vigenere key
     */
    public static String getVigenereKey() {
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the key
        while (true) {
            System.out.print("Enter the key: ");
            String key = scanner.nextLine();
            // Validate the key limit to alphabet characters only
            if (!key.isEmpty() && key.matches("^[a-zA-Z]+$")) {
                return key.toLowerCase();
            } else {
                System.out.println("Please enter a valid key (alphabet characters only).");
            }
        }
    }

    /**
     * Get the password from the user and format it
     * @return the password
     */
    private static String getPassword(){
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the password
        while (true) {
            System.out.print("Enter the password: ");
            String password = scanner.nextLine();
            // Validate the password limit to alphanumeric characters
            if (!password.isEmpty() && password.matches("^[a-zA-Z0-9]+$")) {
                // Remove all non-alphabetic characters and convert to lowercase
                return password.replaceAll("[^a-zA-Z]", "").toLowerCase();
            } else {
                System.out.println("Please enter a valid password (alphanumeric characters only).");
            }
        }
    }

}
