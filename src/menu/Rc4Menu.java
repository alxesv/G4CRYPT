package menu;

import encryption.Rc4;
import utils.Common;

import java.util.Scanner;

public class Rc4Menu {
    /**
     * Display RC4 menu
     */
    public static void RC4() throws Exception {
        System.out.println("--- RC4 Encryption ---\n");

        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        String password = getPassword();
        // Ask a seed to get a random factor in LFSR
        String seed = getSeed();

        // Display the service
        System.out.println("Encrypting password for service: " + service);

        // Encrypt the password
        String encryptedPassword = Rc4.encrypt(password, seed);
        System.out.println("Encrypted password: " + encryptedPassword);
        System.out.println("Seed used to encrypt password: " + seed);

        // Decrypt the password
        // TODO Remove this and add it to the decryption menu
        String decryptedPassword = Rc4.decrypt(encryptedPassword, seed);
        System.out.println("Decrypted password: " + decryptedPassword);

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

    private static String getSeed() {
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the password
        while (true) {
            System.out.print("Enter a seed (required to retrieve password later): ");
            String seed = scanner.nextLine();
            // Validate the password
            if (!seed.isEmpty()) {
                return seed;
            } else {
                System.out.println("Please enter a valid seed.");
            }
        }
    }
}
