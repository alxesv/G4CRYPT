package menu;

import encryption.Rot;
import encryption.Vigenere;
import utils.Common;

import java.util.Scanner;

public class RotationMenu {

    /**
     * Display the Rotation menu
     */
    public static void rotation() {
        System.out.println("--- Rotation Menu --- \n");
        // Ask the name of the service
        String service = Common.getServiceName();
        // Ask the password to encrypt
        String password = getPassword();
        // Ask the key to encrypt the password
        String rot = getRotateKey();

        // Display the service and key
        System.out.println("Encrypting password for service: " + service + " using key: " + rot);

        // Encrypt the password
        String encryptedPassword = Rot.encryptRot(password, rot);
        System.out.println("Encrypted password: " + encryptedPassword);

        // Decrypt the password
        // TODO Remove this and add it to the decryption menu
        String decryptedPassword = Vigenere.decrypt(encryptedPassword, rot);
        System.out.println("Decrypted password: " + decryptedPassword);
    }

    /**
     * Get the password from the user and format it
     * @return
     */

    private static String getPassword(){
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the password
        while (true) {
            System.out.print("Enter the password: ");
            String password = scanner.nextLine();
            // Validate the password limit to alphabet characters only
            if (!password.isEmpty() && Character.isLetter(password)) {
                return password.toLowerCase();
            } else {
                System.out.println("Please enter a valid password (alphabet characters only).");
            }
        }
}
