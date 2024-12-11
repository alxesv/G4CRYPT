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
}
