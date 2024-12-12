package encryption;

public class Vigenere {

        /**
         * Encrypts a given message using the Vigenère cipher.
         *
         * @param messageToEncrypt The message to encrypt.
         * @param key The encryption key.
         * @return The encrypted message.
         */
        public static String encrypt(String messageToEncrypt, String key) {
            messageToEncrypt = messageToEncrypt.toLowerCase();
            key = key.toLowerCase();
            StringBuilder encryptedMessage = new StringBuilder();
            char charToEncrypt;
            int charToEncryptAsciiValue;
            char keyChar;
            int KeyCharAsciiValue;
            int messageLength = messageToEncrypt.length();
            int keyLength = key.length();

            for (int i = 0; i < messageLength; i++) {
                charToEncrypt = messageToEncrypt.charAt(i);
                keyChar = key.charAt((i % keyLength));
                charToEncryptAsciiValue = charToEncrypt;
                KeyCharAsciiValue = keyChar;

                // Compute the encrypted character's ASCII value:
                // 1. Add ASCII values of the message character and key character.
                // 2. Subtract 192 to normalize to alphabetic range.
                // 3. Apply modulo 26 to cycle through alphabet.
                // 4. Add 97 to map back to ASCII lowercase letters.
                int encryptedLetterAsciiValue = ((((charToEncryptAsciiValue-1 + KeyCharAsciiValue-1) - 192) % 26) + 97);

                encryptedMessage.append((char) encryptedLetterAsciiValue);
            }
            return encryptedMessage.toString();
        }

        /**
         * Decrypts an encrypted message using the Vigenère cipher.
         *
         * @param encryptedMessage The encrypted message to decrypt.
         * @param key The decryption key.
         * @return The decrypted message.
         */
        public static String decrypt(String encryptedMessage, String key) {
            encryptedMessage = encryptedMessage.toLowerCase();
            key = key.toLowerCase();
            StringBuilder decryptedMessage = new StringBuilder();
            char encryptedChar;
            int encryptedCharAsciiValue;
            char keyChar;
            int KeyCharAsciiValue;
            int messageLength = encryptedMessage.length();
            int keyLength = key.length();

            for (int i = 0; i < messageLength; i++) {
                encryptedChar = encryptedMessage.charAt(i);
                keyChar = key.charAt((i % keyLength));
                encryptedCharAsciiValue = encryptedChar;
                KeyCharAsciiValue = keyChar;

                // Compute the decrypted character's ASCII value:
                // 1. Subtract ASCII value of key character from encrypted character.
                // 2. Add 26 to handle negative results (alphabet wrap-around).
                // 3. Apply modulo 26 to cycle through alphabet.
                // 4. Add 97 to map back to ASCII lowercase letters.
                int decryptedLetterAsciiValue = ((((encryptedCharAsciiValue - KeyCharAsciiValue) + 26) % 26) + 97);

                decryptedMessage.append((char) decryptedLetterAsciiValue);
            }
            return decryptedMessage.toString();
        }

}
