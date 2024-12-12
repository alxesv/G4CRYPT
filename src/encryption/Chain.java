package encryption;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class Chain {

    /**
     * Handle chain encryption
     * @param password the password to encrypt
     * @param methodVariables the method and variables for each encryption method
     * @return the encrypted password
     */
    public static String handleChainEncryption(String password, String methodVariables) {
        String encryptedPassword = password;
        // Parse the method and variables string into a map
        LinkedHashMap<String, String> methodsMap = parseStringToMap(methodVariables);

        // Iterate over the map and apply the encryption methods
        for (Map.Entry<String, String> entry : methodsMap.entrySet()) {
            String method = entry.getKey();
            String variables = entry.getValue();

            // Handle different encryption methods
            switch (method) {
                case "ROT":
                    encryptedPassword = Rot.encryptRot(encryptedPassword, Integer.parseInt(variables));
                    System.out.println("Encrypted password with ROT :  " + encryptedPassword);
                    break;
                case "VIGENERE":
                    encryptedPassword = Vigenere.encrypt(encryptedPassword, variables);
                    System.out.println("Encrypted password with Vigenere :  " + encryptedPassword);
                    break;
                case "ENIGMA":
                    // Extract the rotor positions
                    String[] rotorPositions = variables.split(",");
                    if (rotorPositions.length != 3) {
                        return "Error: Incorrect number of rotors. Expected 3.";
                    }
                    int rotor1 = Integer.parseInt(rotorPositions[0].trim());
                    int rotor2 = Integer.parseInt(rotorPositions[1].trim());
                    int rotor3 = Integer.parseInt(rotorPositions[2].trim());
                    Enigma enigma = new Enigma(rotor1, rotor2, rotor3);
                    encryptedPassword = enigma.encrypt(encryptedPassword);
                    System.out.println("Encrypted password with Enigma :  " + encryptedPassword);
                    break;
                case "AES":
                    try {
                        // Decode the secret key
                        byte[] decodedKey = Base64.getDecoder().decode(variables);
                        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
                        encryptedPassword = Aes.encrypt(encryptedPassword, key);
                    } catch (IllegalArgumentException e) {
                        return "Error: Invalid key format.";
                    } catch (Exception e) {
                        return "Error: Decryption failed - " + e.getMessage();
                    }
                    System.out.println("Encrypted password with AES :  " + encryptedPassword);
                    break;
                case "RC4":
                    encryptedPassword = Rc4.encrypt(encryptedPassword, variables);
                    System.out.println("Encrypted password with RC4 :  " + encryptedPassword);
                    break;
                case "POLYBIUS":
                    char[][] polybiusSquare = Polybius.stringToGrid(variables);
                    encryptedPassword = Polybius.encrypt(encryptedPassword, polybiusSquare);
                    System.out.println("Encrypted password with Polybius :  " + encryptedPassword);
                    break;
            }
        }
        // Return the password after applying all encryption methods
        return encryptedPassword;
    }

    /**
     * Handle chain decryption
     * @param encryptedPassword the encrypted password
     * @param methodVariables the method and variables for each encryption method
     * @return the decrypted password
     */
    public static String handleChainDecryption(String encryptedPassword, String methodVariables) {
        String decryptedPassword = encryptedPassword;
        // Parse the method and variables string into a map
        Map<String, String> methodsMap = parseStringToMap(methodVariables);

        // Reverse the list of entries
        List<Map.Entry<String, String>> reversedMethodsMap = new ArrayList<>(methodsMap.entrySet());
        Collections.reverse(reversedMethodsMap);

        // Iterate over the reversed list and apply the decryption methods
        for (Map.Entry<String, String> entry : reversedMethodsMap) {
            String method = entry.getKey();
            String variables = entry.getValue();

            // Handle different decryption methods
            switch (method) {
                case "ROT":
                    decryptedPassword = Rot.decryptRot(decryptedPassword, Integer.parseInt(variables));
                    System.out.println("Decrypted password with ROT :  " + decryptedPassword);
                    break;
                case "VIGENERE":
                    decryptedPassword = Vigenere.decrypt(decryptedPassword, variables);
                    System.out.println("Decrypted password with Vigenere :  " + decryptedPassword);
                    break;
                case "ENIGMA":
                    // Extract the rotor positions
                    String[] rotorPositions = variables.split(",");
                    if (rotorPositions.length != 3) {
                        return "Error: Incorrect number of rotors. Expected 3.";
                    }
                    int rotor1 = Integer.parseInt(rotorPositions[0].trim());
                    int rotor2 = Integer.parseInt(rotorPositions[1].trim());
                    int rotor3 = Integer.parseInt(rotorPositions[2].trim());
                    Enigma enigma = new Enigma(rotor1, rotor2, rotor3);
                    decryptedPassword = enigma.decrypt(decryptedPassword);
                    System.out.println("Decrypted password with Enigma :  " + decryptedPassword);
                    break;
                case "AES":
                    try {
                        // Decode the secret key
                        byte[] decodedKey = Base64.getDecoder().decode(variables);
                        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
                        decryptedPassword = Aes.decrypt(decryptedPassword, key);
                    } catch (IllegalArgumentException e) {
                        return "Error: Invalid key format.";
                    } catch (Exception e) {
                        return "Error: Decryption failed - " + e.getMessage();
                    }
                    System.out.println("Decrypted password with AES :  " + decryptedPassword);
                    break;
                case "RC4":
                    decryptedPassword = Rc4.decrypt(decryptedPassword, variables);
                    System.out.println("Decrypted password with RC4 :  " + decryptedPassword);
                    break;
                case "POLYBIUS":
                    char[][] polybiusSquare = Polybius.stringToGrid(variables);
                    decryptedPassword = Polybius.decrypt(decryptedPassword, polybiusSquare);
                    System.out.println("Decrypted password with Polybius :  " + decryptedPassword);
                    break;

            }
        }

        // Return the decrypted password
        return decryptedPassword;
    }

    /**
     * Convert a structured string into a simple map.
     *
     * @param input the structured string
     * @return a map with key-value pairs
     */
    public static LinkedHashMap<String, String> parseStringToMap(String input) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();

        // Split the input string by the ";" separator
        String[] items = input.split(";");
        for (String item : items) {
            if (!item.isEmpty()) {
                // Split each item into key and value by the ">" separator
                String[] keyValue = item.split(">");
                if (keyValue.length == 2) {
                    resultMap.put(keyValue[0], keyValue[1]);
                }
            }
        }

        return resultMap;
    }
}
