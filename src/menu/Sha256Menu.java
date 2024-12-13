package menu;

import hash.Sha256;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import java.util.Scanner;

import javax.crypto.SecretKey;

public class Sha256Menu {
    /**
     * Display Sha256 menu
     */
    public static void Sha256() throws Exception {
        Common.printTitle("SHA256 HASH MENU", "Welcome to the SHA256 hash method Menu!");

        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to hash
        String password = getPassword();

        // Display the service
        System.out.println("Encrypting password for service: " + service);

        // Hash the password
        String hashedPassword = Sha256.hashString(password);
        System.out.println("Encrypted password: " + hashedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted password and the seed
        SaveData.saveData(service, hashedPassword, "SHA256", null, aesKey);

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
