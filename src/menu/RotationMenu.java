package menu;

import encryption.Rot;
import encryption.Vigenere;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.SaveData.saveData;

public class RotationMenu {

    private static List<String> passwordData = new ArrayList<>();

    /**
     * Display the Rotation menu
     */
    public static void rotation() throws Exception {
        Common.printTitle("ROTATION MENU", "Welcome to the ROTATION encryption Menu!");
        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        String password = getPassword();


        // Ask the key to encrypt the password
        int rot = getRotateKey();


        // Display the service and key
        System.out.println("\u001B[36mEncrypting password for service: \u001B[0m" + service + " \u001B[36musing key: \u001B[0m" + rot);

        // Encrypt the password
        String encryptedPassword = Rot.encryptRot(password, rot);
        System.out.println("\u001B[36mEncrypted password: \u001B[0m" + encryptedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted data
        SaveData.saveData(service, encryptedPassword, "ROT", String.valueOf(rot), aesKey);
    }

    /**
     * Get the password from the user and format it
     *
     * @return
     */

    private static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the password
        while (true) {
            System.out.print("\u001B[36mEnter the password: \u001B[0m");
            String password = scanner.nextLine();
            // Remove spaces from the password
            password = password.replaceAll(" ", "");
            // Validate the password limit to alphabet characters only
            if (!password.isEmpty() && password.matches("[a-zA-Z]+")) {
                // Validate the password limit to upper or lower case only
                if(password.equals(password.toLowerCase()) || password.equals(password.toUpperCase())){
                    return password;
                } else {
                    System.out.println("\u001B[31mPlease enter a valid password (upper or lower case).\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mPlease enter a valid password (alphabet characters only).\u001B[0m");
            }
        }
    }

    /**
     * Get the Rotation key from the user
     *
     * @return the Rotation key
     */

    public static int getRotateKey() {
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the key
        while (true) {
            System.out.print("\u001B[36mEnter the key: \u001B[0m");
            String key = scanner.nextLine();
            // Validate the key limit to alphabet characters only
            if (!key.isEmpty() && key.matches("^-?(?:[1-9]|1[0-9]|2[0-5])$")) {
                return Integer.parseInt(key);
            } else {
                System.out.println("\u001B[31mPlease enter a valid key (only a number between 1 and 25 or -1 and -25).\u001B[0m");
            }
        }
    }
}
