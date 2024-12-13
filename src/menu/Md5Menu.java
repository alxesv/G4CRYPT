package menu;

import hash.Md5;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import java.util.Scanner;

import javax.crypto.SecretKey;

public class Md5Menu {
    /**
     * Display Md5 menu
     */
    public static void MD5() throws Exception {
        System.out.println("--- MD5 Hashing ---\n");

        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the stuff to hash
        String password = getPassword();

        // Display the service
        System.out.println("Hashing password for service: " + service);

        // Hash the password
        String hashedPassword = Md5.hashString(password);
        System.out.println("Hashed password: " + hashedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted password and the seed
        SaveData.saveData(service, hashedPassword, "MD5", null, aesKey);
        
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
