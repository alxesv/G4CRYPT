package encryption;

public class Polybius {
    // The Polybius square grid used for encryption and decryption
    private static char[][] polybeGrid = {
            {'a', 'n', 'z', 'b', 'e'},
            {'v', 'r', 'c', 't', 'x'},
            {'y', 'u', 'q', 'i', 's'},
            {'o', 'd', 'p', 'f', 'm'},
            {'g', 'l', 'h', 'k', 'j'}
    }; // W = VV

    /**
     * Encrypts a given plaintext string using the Polybius cipher.
     * @param text The input plaintext to be encrypted.
     * @return A string containing the encrypted representation of the input text.
     */
    public static String encrypt(String text) {
        // Remove special characters and whitespace from the text
        text = text.replaceAll("[^a-zA-Z]", "");
        text = text.toLowerCase(); // Transform to lowercase

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
    public static String decrypt(String encryptedText){
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

}
