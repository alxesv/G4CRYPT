package encryption;

public class Enigma {
    // Rotor configurations (substitution ciphers)
    private static final String ROTOR1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    private static final String ROTOR2 = "BJDKSIRUXALHWTMCQGZNPYFVOE";
    private static final String ROTOR3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    private static final String ROTOR4 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
    private static final String ROTOR5 = "VZBRGITYUPSDNHLXAWMJQOFECK";
    private static final String ROTOR6 = "JPGVOUMFYQBENHZRDKASXLICTW";

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Rotor positions
    private int rotor1Position;
    private int rotor2Position;
    private int rotor3Position;

    /**
     * Constructor to initialize the rotors' starting positions.
     *
     * @param rotor1Start Initial position of rotor 1 (0-25).
     * @param rotor2Start Initial position of rotor 2 (0-25).
     * @param rotor3Start Initial position of rotor 3 (0-25).
     */
    public Enigma(int rotor1Start, int rotor2Start, int rotor3Start) {
        this.rotor1Position = rotor1Start % 26;
        this.rotor2Position = rotor2Start % 26;
        this.rotor3Position = rotor3Start % 26;
    }

    /**
     * Encrypts a given plaintext message.
     *
     * @param text The plaintext message to encrypt.
     * @return The encrypted ciphertext.
     */
    public String encrypt(String text) {
        text = cleanText(text);
        StringBuilder encryptedText = new StringBuilder();

        for (char currentCharacter : text.toCharArray()) {
            encryptedText.append(encryptCharacter(currentCharacter));
        }
        return encryptedText.toString();
    }

    /**
     * Decrypts a given ciphertext message.
     *
     * @param text The ciphertext message to decrypt.
     * @return The decrypted plaintext.
     */
    public String decrypt(String text) {
        text = cleanText(text);
        StringBuilder decryptedText = new StringBuilder();

        for (char currentCharacter : text.toCharArray()) {
            decryptedText.append(decryptCharacter(currentCharacter));
        }
        return decryptedText.toString();
    }

    /**
     * Cleans the input text by removing non-alphabetic characters and converting to uppercase.
     *
     * @param text The input text to clean.
     * @return The cleaned text.
     */
    private String cleanText(String text) {
        return text.replaceAll("[^a-zA-Z]", "").toUpperCase();
    }

    /**
     * Encrypts a single character using the 6 rotors.
     *
     * @param letter The character to encrypt.
     * @return The encrypted character.
     */
    private char encryptCharacter(char letter) {
        int index = ALPHABET.indexOf(letter);

        // Pass through Rotor 1 (left to right)
        index = (index + rotor1Position) % 26;
        char rotor1Char = ROTOR1.charAt(index);

        // Pass through Rotor 2 (left to right)
        index = (ALPHABET.indexOf(rotor1Char) + rotor2Position) % 26;
        char rotor2Char = ROTOR2.charAt(index);

        // Pass through Rotor 3 (left to right)
        index = (ALPHABET.indexOf(rotor2Char) + rotor3Position) % 26;
        char rotor3Char = ROTOR3.charAt(index);

        // Mirror through the alphabet
        index = ALPHABET.indexOf(rotor3Char);
        char mirroredChar = ALPHABET.charAt((25 - index) % 26);

        // Pass through Rotor 4 (right to left)
        index = (ALPHABET.indexOf(mirroredChar) + rotor3Position) % 26;
        char rotor4Char = ROTOR4.charAt(index);

        // Pass through Rotor 5 (right to left)
        index = (ALPHABET.indexOf(rotor4Char) + rotor2Position) % 26;
        char rotor5Char = ROTOR5.charAt(index);

        // Pass through Rotor 6 (right to left)
        index = (ALPHABET.indexOf(rotor5Char) + rotor1Position) % 26;
        return ROTOR6.charAt(index);
    }

    /**
     * Decrypts a single character using the 6 rotors.
     *
     * @param letter The character to decrypt.
     * @return The decrypted character.
     */
    private char decryptCharacter(char letter) {
        int index = ROTOR6.indexOf(letter);

        // Undo pass through Rotor 6 (right to left)
        index = (index - rotor1Position + 26) % 26;
        char rotor5Char = ALPHABET.charAt(index);

        // Undo pass through Rotor 5 (right to left)
        index = ROTOR5.indexOf(rotor5Char);
        index = (index - rotor2Position + 26) % 26;
        char rotor4Char = ALPHABET.charAt(index);

        // Undo pass through Rotor 4 (right to left)
        index = ROTOR4.indexOf(rotor4Char);
        index = (index - rotor3Position + 26) % 26;
        char mirroredChar = ALPHABET.charAt(index);

        // Undo mirror through the alphabet
        index = ALPHABET.indexOf(mirroredChar);
        char rotor3Char = ALPHABET.charAt((25 - index) % 26);

        // Undo pass through Rotor 3 (left to right)
        index = ROTOR3.indexOf(rotor3Char);
        index = (index - rotor3Position + 26) % 26;
        char rotor2Char = ALPHABET.charAt(index);

        // Undo pass through Rotor 2 (left to right)
        index = ROTOR2.indexOf(rotor2Char);
        index = (index - rotor2Position + 26) % 26;
        char rotor1Char = ALPHABET.charAt(index);

        // Undo pass through Rotor 1 (left to right)
        index = ROTOR1.indexOf(rotor1Char);
        index = (index - rotor1Position + 26) % 26;

        return ALPHABET.charAt(index);
    }
}
