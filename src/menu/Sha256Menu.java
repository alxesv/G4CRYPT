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
        System.out.println("\u001B[36mEncrypting password for service: \u001B[0m" + service);

        // Hash the password
        String hashedPassword = Sha256.hashString(password);
        System.out.println("\u001B[36mEncrypted password: \u001B[0m" + hashedPassword);

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
            System.out.print("\u001B[36mEnter the password: \u001B[0m");
            String password = scanner.nextLine();
            // Validate the password
            if (!password.isEmpty()) {
                return password;
            } else {
                System.out.println("\u001B[31mPlease enter a valid password.\u001B[31m");
            }
        }
    }
}
