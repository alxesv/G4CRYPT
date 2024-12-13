package menu;

import encryption.*;
import utils.Common;
import utils.HashIntegrityChecker;
import utils.RetrieveCSV;
import hash.Hmac;
import hash.Md5;
import hash.Sha256;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
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
        Common.clearScreen();

        Common.printTitle("RETRIEVE MENU", "Welcome to the RETRIEVE Menu!");

        // Retrieve the list of passwords from the CSV file
        List<String> list = RetrieveCSV.getListCSV();

        if (list.isEmpty()) {
            System.out.println("\u001B[37mNo data found in the CSV file.\u001B[0m");
            return;
        }

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            // Display the list of passwords
            System.out.println("\u001B[36mAvailable passwords:\u001B[0m");
            for (int i = 0; i < list.size(); i++) {
                String[] parts = list.get(i).split(":");
                String name = parts[0];
                String password = parts[1];
                System.out.println("\u001B[1;36m" + (i + 1) + "\u001B[0m - \u001B[36mName: \u001B[0m" + name + ", \u001B[36mPassword: \u001B[0m" + password);
            }

            // Prompt the user for input
            System.out.print("\u001B[36mEnter the corresponding number to retrieve the password, or '0' to quit: \u001B[0m");
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

                        String decryptedPassword = "";

                        if (method.equals("MD5") || method.equals("SHA256") || method.equals("HMAC")) {
                            System.out.print("\u001B[36mEnter password to compare with stored hash: \u001B[0m");
                            decryptedPassword = decryptPassword(encryptedPassword, method, args, Optional.of(scanner.nextLine()));
                        } else {
                            // Decrypt the password
                            decryptedPassword = decryptPassword(encryptedPassword, method, args, Optional.empty());
                        }

                        // Display the result
                        System.out.println("\n\u001B[1;37mName: \u001B[0m" + name);
                        System.out.println("\u001B[1;37mDecrypted Password: \u001B[0m" + decryptedPassword + "\n");

                        Common.promptToContinue(scanner);
                    } else {
                        System.out.println("\u001B[31mInvalid number. Please choose a valid index.\u001B[0m");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n\u001B[31mInvalid input. Please enter a number or '0' to quit.\u001B[0m");
                }
            }
        }
        System.out.println("\u001B[33mExiting...\u001B[0m");
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
    public static String decryptPassword(String encryptedPassword, String method, String args, Optional<String> passwordToCompare) {
        String password = "";

        switch (method.toUpperCase()) {
            case "ROT":
                if(args != null) {
                    int X = Integer.parseInt(args);
                    password = Rot.decryptRot(encryptedPassword, X);
                    return password;
                }

                return "\u001B[31mError: No rotation index provided.\u001B[0m";

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
                        return "\u001B[31mError: Incorrect number of rotors. Expected 3.\u001B[0m";
                    }
                }
                return "\u001B[31mError: No arguments provided for ENIGMA.\u001B[0m";

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
                        return "\u001B[31mError: Invalid key format.\u001B[0m";
                    } catch (Exception e) {
                        return "\u001B[31mError: Decryption failed - " + e.getMessage() + "\u001B[0m";
                    }
                }
                return "\u001B[31mError: No key provided for AES.\u001B[0m";

            case "VIGENERE":
                // Decrypt using Vigenere cipher
                if (args != null) {
                    password = Vigenere.decrypt(encryptedPassword, args);
                    return password;
                }
                return "\u001B[31mWe encountered a problem decrypting your password, please wait.\u001B[0m";

            case "POLYBIUS":
                // Decrypt using Polybius square cipher
                if(args != null) {
                    char[][] grid = Polybius.stringToGrid(args);
                    password = Polybius.decrypt(encryptedPassword, grid);
                    return password;
                }
                return "\u001B[31mError: No grid provided for Polybius.\u001B[0m";

            case "RC4":
                // Decrypt using RC4 cipher
                if(args != null) {
                    password = Rc4.decrypt(encryptedPassword, args);
                    return password;
                }
                return "\u001B[31mError: No seed provided.\u001B[0m";

            case "CHAIN":
                // Decrypt using a chain of encryption methods
                password = Chain.handleChainDecryption(encryptedPassword, args);
                return password;

            case "MD5":
                // transforms Optional<String> passwordToCompare to String type
                String passwordToCompareMd5 = passwordToCompare.get();

                // Compares both passwords using MD5
                if (HashIntegrityChecker.Checker(method, passwordToCompareMd5, encryptedPassword, Optional.empty())) {
                    return passwordToCompareMd5;
                } else {
                    return "\u001B[31mPasswords not matching\u001B[0m";
                }

            case "SHA256":
                // transforms Optional<String> passwordToCompare to String type
                String passwordToCompareSha256 = passwordToCompare.get();

                // Compares both passwords using SHA256
                if (HashIntegrityChecker.Checker(method, passwordToCompareSha256, encryptedPassword, Optional.empty())) {
                    return passwordToCompareSha256;
                } else {
                    return "\u001B[31mPasswords not matching\u001B[0m";
                }

            case "HMAC":
                // transforms Optional<String> passwordToCompare to String type
                String passwordToCompareHmac = passwordToCompare.get();

                // Compares both passwords using HMAC
                if(HashIntegrityChecker.Checker(method, passwordToCompareHmac, encryptedPassword, Optional.of(args))) {
                    return passwordToCompareHmac;
                } else {
                    return "\u001B[31mPasswords not matching\u001B[0m";
                }

            default:
                return "\u001B[31mUnsupported decryption method: " + method + "\u001B[0m";
        }
    }
}
