package menu;

import hash.Sha256;
import utils.Common;

import java.util.Scanner;

public class Sha256Menu {
    /**
     * Display Sha256 menu
     */
    public static void Sha256() throws Exception {
        System.out.println("--- Sha256 Hashing ---\n");

        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        String password = getPassword();

        // Display the service
        System.out.println("Encrypting password for service: " + service);

        // Hash the password
        String encryptedPassword = Sha256.hashString(password);
        System.out.println("Encrypted password: " + encryptedPassword);

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
