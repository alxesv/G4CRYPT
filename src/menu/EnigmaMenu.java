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
        Common.clearScreen();

        // Display menu title
        printEnigmaTitle();

        // Ask the name of the service
        String service = Common.getServiceName();

        // Ask the password to encrypt
        String password = Common.getAlphabetCharactersOnly();

        Scanner scanner = new Scanner(System.in);
        int rotor1, rotor2, rotor3;

        System.out.print("\u001B[36mDo you want to choose the rotor positions? (yes/no): \u001B[0m");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes") || choice.equals("y")) {
            // Let the user input rotor positions
            rotor1 = getPositiveInt(scanner, "\u001B[36mEnter position for rotor 1 (0-25): \u001B[0m");
            rotor2 = getPositiveInt(scanner, "\u001B[36mEnter position for rotor 2 (0-25): \u001B[0m");
            rotor3 = getPositiveInt(scanner, "\u001B[36mEnter position for rotor 3 (0-25): \u001B[0m");
        } else {
            // Generate random rotor positions
            rotor1 = new java.util.Random().nextInt(26);
            rotor2 = new java.util.Random().nextInt(26);
            rotor3 = new java.util.Random().nextInt(26);

            System.out.println("\u001B[33mRandom rotor positions selected:\u001B[0m Rotor 1: " + rotor1 + ", Rotor 2: " + rotor2 + ", Rotor 3: " + rotor3);
        }

        // Create Enigma instance and encrypt password
        Enigma enigma = new Enigma(rotor1, rotor2, rotor3);
        String encryptedPassword = enigma.encrypt(password);

        System.out.println("\u001B[32mEncrypted Password:\u001B[0m " + encryptedPassword);

        // Retrieve AES Key
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
    public static int getPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= 0 && value <= 25) {
                    return value;
                } else {
                    System.out.println("\u001B[31mError: Please enter a number between 0 and 25.\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mError: Invalid input. Please enter a valid integer.\u001B[0m");
            }
        }
    }

    /**
     * Print the ENIGMA menu title
     */
    private static void printEnigmaTitle() {
        System.out.println("\u001B[1;34m==================== ENIGMA ENCRYPTION ====================\u001B[0m");
        System.out.println("\u001B[1;32m        Welcome to the ENIGMA Encryption Menu!             \u001B[0m");
        System.out.println("\u001B[1;34m==========================================================\u001B[0m");
    }
}
