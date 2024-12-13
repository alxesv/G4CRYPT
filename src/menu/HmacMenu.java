package menu;

import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;
import hash.Hmac;

import javax.crypto.SecretKey;
import java.util.Scanner;

public class HmacMenu {
    /**
     * Display HMAC menu
     */
    public static void HMAC() throws Exception {
        Common.printTitle("HMAC HASH MENU", "Welcome to the HMAC hash method Menu!");


        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        String password = getPassword();
        // Ask a seed to get a random factor in LFSR
        String seed = getSeed();

        // Display the service
        System.out.println("Encrypting password for service: " + service);

        // Encrypt the password
        String hashedPassword = Hmac.hashString(password, seed);
        System.out.println("Encrypted password: " + hashedPassword);
        System.out.println("Seed used to encrypt password: " + seed);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted password and the seed
        SaveData.saveData(service, hashedPassword, "HMAC", seed, aesKey);
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

    /**
     * Get a seed to initiate Salt
     * @return the seed
     */

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
