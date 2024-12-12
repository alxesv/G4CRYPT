package menu;

import encryption.Enigma;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import javax.crypto.SecretKey;
import java.util.Scanner;

public class EnigmaMenu {
    /**
     * Display the ENIGMA menu
     */
    public static void enigma() throws Exception {
        System.out.println("--- ENIGMA Encryption ---\n");

        // Ask the name of the service
        String service = Common.getServiceName();

        // Ask the password to encrypt
        String password = getPassword();

        Scanner scanner = new Scanner(System.in);
        int rotor1, rotor2, rotor3;

        System.out.print("Do you want to choose the rotor positions? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes") || choice.equals("y")) {
            // Let the user input rotor positions
            rotor1 = getPositiveInt(scanner, "Enter position for rotor 1 (0-25): ");
            rotor2 = getPositiveInt(scanner, "Enter position for rotor 2 (0-25): ");
            rotor3 = getPositiveInt(scanner, "Enter position for rotor 3 (0-25): ");
        } else {
            // Generate random rotor positions
            rotor1 = new java.util.Random().nextInt(26);
            rotor2 = new java.util.Random().nextInt(26);
            rotor3 = new java.util.Random().nextInt(26);

            System.out.println("Random rotor positions selected: Rotor 1: " + rotor1 + ", Rotor 2: " + rotor2 + ", Rotor 3: " + rotor3);
        }

        // Create Enigma instance and encrypt password
        Enigma enigma = new Enigma(rotor1, rotor2, rotor3);
        String encryptedPassword = enigma.encrypt(password);

        System.out.println("Encrypted Password: " + encryptedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted password and rotor positions
        String rotorPositions = rotor1 + "," + rotor2 + "," + rotor3;
        SaveData.saveData(service, encryptedPassword, "ENIGMA", rotorPositions, aesKey);
    }

    /**
     * Helper method to get a positive integer input from the user.
     * Ensures the input is valid and within the specified range.
     *
     * @param scanner Scanner object for input
     * @param prompt  Prompt message to display to the user
     * @return A positive integer within the range 0-25
     */
    private static int getPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Error: Please enter a positive number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a positive integer.");
            }
        }
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
