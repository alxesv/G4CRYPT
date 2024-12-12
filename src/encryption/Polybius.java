package encryption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polybius {
    /**
     * Encrypts a given plaintext string using the Polybius cipher.
     * @param text The input plaintext to be encrypted.
     * @return A string containing the encrypted representation of the input text.
     */
    public static String encrypt(String text, char[][] polybeGrid) {
        // Remove special characters and whitespace from the text
        text = text.replaceAll("[^a-zA-Z]", "");
        text = text.toUpperCase(); // Transform to lowercase

        StringBuilder polybeString = new StringBuilder();

        // Loop on every letter of the text
        for (int i = 0; i < text.length(); i++) {
            // Get the letter we're working on
            char currentChar = text.charAt(i);

            // Special case for 'w' treated as 'vv'
            if (currentChar == 'w') {
                for (int row = 0; row < polybeGrid.length; row++) {
                    for (int col = 0; col < polybeGrid[row].length; col++) {
                        if (polybeGrid[row][col] == 'v') {
                            polybeString.append((row + 1)).append((col + 1)); // Position of 'v'
                            polybeString.append((row + 1)).append((col + 1)); // Position of 'v' again
                            break; // Exit the loop once 'w' is found
                        }
                    }
                }
            }else{
                // Find the character in the grid and get its position
                boolean found = false;
                for (int row = 0; row < polybeGrid.length; row++) {
                    for (int col = 0; col < polybeGrid[row].length; col++) {
                        if (polybeGrid[row][col] == currentChar) {
                            polybeString.append((row + 1)).append((col + 1)); // Append position as row+col
                            found = true;
                            break;
                        }
                    }
                    if (found) break; // Exit the loop once the character is found
                }
            }
        }

        return polybeString.toString(); // Return the final encoded string
    }

    /**
     * Decrypts a given encrypted string using the Polybius cipher.
     * @param encryptedText The input encrypted text to be decrypted.
     * @return A string containing the original plaintext.
     */
    public static String decrypt(String encryptedText, char[][] polybeGrid){
        StringBuilder decryptedText = new StringBuilder();

        // Loop through the encrypted text two characters at a time
        for (int i = 0; i < encryptedText.length(); i += 2) {
            // Extract row and column from the text
            int row = Character.getNumericValue(encryptedText.charAt(i)) - 1; // Convert char to int, adjust to zero index
            int col = Character.getNumericValue(encryptedText.charAt(i + 1)) - 1; // Same for column

            // Handle invalid inputs (optional)
            if (row < 0 || row >= polybeGrid.length || col < 0 || col >= polybeGrid[row].length) {
                throw new IllegalArgumentException("Invalid encrypted text: " + encryptedText);
            }

            // Retrieve the character from the grid
            char decodedChar = polybeGrid[row][col];
            decryptedText.append(decodedChar); // Append the character to the result
        }

        // Return the decrypted text
        return decryptedText.toString();
    }

    /**
     * Convert the grid to a string representation
     * @param grid the Polybius grid
     * @return the grid as a string
     */
    public static String gridToString(char[][] grid) {
        StringBuilder builder = new StringBuilder();
        for (char[] row : grid) {
            for (char c : row) {
                builder.append(c);
            }
        }
        return builder.toString();
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

    /**
     * Generate the Polybius grid (5x5 without 'J')
     * @return a 5x5 char grid
     */
    public static char[][] generatePolybiusGrid() {
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
}
