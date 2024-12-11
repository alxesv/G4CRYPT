package menu;

import encryption.Aes;
import encryption.Polybius;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PolybiusMenu {
    /**
     * Display the Polybius menu
     */
    public static void polybius() throws Exception {
        System.out.println("--- POLYBIUS Encryption ---\n");

        // Inform the user about the encryption behavior
        System.out.println("Note: Only letters will be encrypted. Spaces, numbers, and special characters will be removed.");
        System.out.println("The text will also be converted to uppercase before encryption.\n");

        // Ask the name of the service
        String service = Common.getServiceName();

        // Ask the password to encrypt
        String password = getPassword();

        // Generate and display the Polybius grid
        char[][] grid = generatePolybiusGrid();
        System.out.println("Polybius Grid:");
        displayGrid(grid);

        // Encrypt the password using Polybius
        String encryptedPassword = Polybius.encrypt(password, grid);
        System.out.println("Encrypted Password: " + encryptedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted data
        String gridAsString = gridToString(grid);
        SaveData.saveData(service, encryptedPassword, "POLYBIUS", gridAsString, aesKey);
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
     * Generate the Polybius grid (5x5 without 'J')
     * @return a 5x5 char grid
     */
    private static char[][] generatePolybiusGrid() {
        char[][] grid = new char[5][5];
        List<Character> letters = new ArrayList<>();

        // Ajouter les lettres de l'alphabet (sans 'J') à une liste
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            if (letter != 'W') { // format W = VV
                letters.add(letter);
            }
        }

        // Mélanger les lettres
        Collections.shuffle(letters);

        // Remplir la grille avec les lettres mélangées
        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                grid[row][col] = letters.get(index);
                index++;
            }
        }

        return grid;
    }

    /**
     * Convert the grid to a string representation
     * @param grid the Polybius grid
     * @return the grid as a string
     */
    private static String gridToString(char[][] grid) {
        StringBuilder builder = new StringBuilder();
        for (char[] row : grid) {
            for (char c : row) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * Display the Polybius grid
     * @param grid the Polybius grid
     */
    private static void displayGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}