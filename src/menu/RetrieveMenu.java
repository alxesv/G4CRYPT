package menu;

import encryption.*;
import utils.Common;
import utils.RetrieveCSV;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

/**
 * RetrieveMenu class provides a menu-based interface for retrieving passwords from a CSV file.
 * It allows the user to decrypt passwords using various decryption methods such as ROT, Enigma, AES, etc.
 */
public class RetrieveMenu {

    /**
     * Main method to handle the password retrieval menu.
     * Displays passwords stored in a CSV file and allows the user to decrypt them using various methods.
     *
     * @throws Exception If any error occurs during the decryption process.
     */
    public static void retrieve() throws Exception {
        System.out.println("--- Retrieve Password Menu ---\n");

        // Retrieve the list of passwords from the CSV file
        List<String> list = RetrieveCSV.getListCSV();

        if (list.isEmpty()) {
            System.out.println("No data found in the CSV file.");
            return;
        }

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            // Display the list of passwords
            System.out.println("Available passwords:");
            for (int i = 0; i < list.size(); i++) {
                String[] parts = list.get(i).split(":");
                String name = parts[0];
                String password = parts[1];
                System.out.println((i + 1) + " - Name: " + name + ", Password: " + password);
            }

            // Prompt the user for input
            System.out.print("\nEnter the corresponding number to retrieve the password, or '0' to quit: ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                running = false;
            } else {
                try {
                    int index = Integer.parseInt(input) - 1;

                    if (index >= 0 && index < list.size()) {
                        // Parse the selected entry
                        String[] parts = list.get(index).split(":");
                        String name = parts[0];
                        String encryptedPassword = parts[1];
                        String method = parts[2];
                        String args = parts.length > 3 ? parts[3] : null;

                        // Decrypt the password
                        String decryptedPassword = decryptPassword(encryptedPassword, method, args);

                        // Display the result
                        System.out.println("\nName: " + name);
                        System.out.println("Decrypted Password: " + decryptedPassword + "\n");
                    } else {
                        System.out.println("Invalid number. Please choose a valid index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number or 'exit'.");
                }
            }
        }

        System.out.println("Exiting...");
    }

    /**
     * Decrypts a password using the specified method and arguments.
     *
     * This method determines which decryption method to use (ROT, ENIGMA, AES, etc.) and applies it to the encrypted password.
     *
     * @param encryptedPassword The encrypted password to decrypt.
     * @param method The decryption method to use (e.g., ROT, ENIGMA, AES, etc.).
     * @param args The arguments required for the decryption method (e.g., key, rotors, etc.).
     * @return The decrypted password, or an error message if decryption fails.
     */
    public static String decryptPassword(String encryptedPassword, String method, String args) {
        String password = "";

        switch (method.toUpperCase()) {
            case "ROT":
                if(args != null) {
                    int X = Integer.parseInt(args);
                    password = Rot.decryptRot(encryptedPassword, X);
                    return password;
                }

                return "Error: No rotation index provided.";

            case "ENIGMA":
                // Decrypt using Enigma machine simulation
                if (args != null) {
                    List<String> rotorList = Arrays.asList(args.split(","));

                    if (rotorList.size() == 3) {
                        int rotor1 = Integer.parseInt(rotorList.get(0).trim());
                        int rotor2 = Integer.parseInt(rotorList.get(1).trim());
                        int rotor3 = Integer.parseInt(rotorList.get(2).trim());

                        Enigma enigma = new Enigma(rotor1, rotor2, rotor3);
                        password = enigma.decrypt(encryptedPassword);

                        return password;
                    } else {
                        return "Error: Incorrect number of rotors. Expected 3.";
                    }
                }
                return "Error: No arguments provided for ENIGMA.";

            case "AES":
                // Decrypt using AES encryption
                if (args != null) {
                    try {
                        // Decode the secret key
                        byte[] decodedKey = Base64.getDecoder().decode(args);
                        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

                        password = Aes.decrypt(encryptedPassword, key);

                        return password;
                    } catch (IllegalArgumentException e) {
                        return "Error: Invalid key format.";
                    } catch (Exception e) {
                        return "Error: Decryption failed - " + e.getMessage();
                    }
                }
                return "Error: No key provided for AES.";

            case "VIGENERE":
                // Decrypt using Vigenere cipher
                if (args != null) {
                    password = Vigenere.decrypt(encryptedPassword, args);
                    return password;
                }
                return "We encountered a problem decrypting your password, please wait.";

            case "POLYBIUS":
                // Decrypt using Polybius square cipher
                if(args != null) {
                    char[][] grid = stringToGrid(args);
                    password = Polybius.decrypt(encryptedPassword, grid);
                    return password;
                }
                return "Error: No grid provided for Polybius.";

            case "RC4":
                // Decrypt using RC4 cipher
                if(args != null) {
                    password = Rc4.Rc4Decrypt(encryptedPassword, args);
                    return password;
                }
                return "Error: No seed provided.";

            default:
                return "Unsupported decryption method: " + method;
        }
    }

    /**
     * Converts a 25-character string into a 5x5 grid (Polybius square).
     * <p>
     * The string must have exactly 25 characters, and the method fills a 5x5 grid with the characters from the string.
     *
     * @param text The string to convert into a 5x5 grid.
     * @return A 5x5 character grid representing the Polybius square.
     * @throws IllegalArgumentException If the input text does not have exactly 25 characters.
     */
    public static char[][] stringToGrid(String text) {
        // Check that the text has exactly 25 characters
        if (text.length() != 25) {
            throw new IllegalArgumentException("Text must have exactly 25 characters.");
        }

        char[][] grid = new char[5][5];

        // Fill the 5x5 grid with characters from the text
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = text.charAt(i * 5 + j);
            }
        }

        return grid;
    }
}
