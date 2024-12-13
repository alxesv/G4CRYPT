package utils;

import java.util.Scanner;

/**
 * Common utility functions
 */
public class Common {
    // Méthode pour effacer la console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Méthode pour attendre une entrée de l'utilisateur avant de continuer
    public static void promptToContinue(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Function to get the name of the service from the user
     */
    public static String getServiceName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\u001B[36mEnter the name of the service: \u001B[0m");
        while (!scanner.hasNext("^[a-zA-Z0-9]+$")) {
            System.out.println("\u001B[31mPlease enter a valid service name (alphanumeric characters only).\u001B[0m");
            System.out.print("\u001B[36mEnter the name of the service: \u001B[0m");
            scanner.next();
        }
        return scanner.next();
    }

    /**
     * Convert a byte array to a hex string
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b); // Convert byte to hex
            if (hex.length() == 1) {
                hexString.append('0'); // Pad with leading zero if needed
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Convert a hex string to a byte array
     * @param hex the hex string
     * @return the byte array
     */
    public static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }
}
