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
        Common.printTitle("MD5 HASH MENU", "Welcome to the MD5 hash method Menu!");


        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the stuff to hash
        String password = getPassword();

        // Display the service
        System.out.println("\u001B[36mHashing password for service: \u001B[0m" + service);

        // Hash the password
        String hashedPassword = Md5.hashString(password);
        System.out.println("\u001B[36mHashed password: \u001B[0m" + hashedPassword);

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
            System.out.print("\u001B[36mEnter the password: \u001B[0m");
            String password = scanner.nextLine();
            // Validate the password
            if (!password.isEmpty()) {
                return password;
            } else {
                System.out.println("\u001B[31mPlease enter a valid password.\u001B[0m");
            }
        }
    }
}
