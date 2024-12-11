package utils;

import java.util.Scanner;

/**
 * Common utility functions
 */
public class Common {
    /**
     * Function to get the name of the service from the user
     */
    public static String getServiceName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the service: ");
        while (!scanner.hasNext("^[a-zA-Z0-9]+$")) {
            System.out.println("Please enter a valid service name (alphanumeric characters only)");
            System.out.print("Enter the name of the service: ");
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
