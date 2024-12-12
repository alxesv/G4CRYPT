package menu;

import java.util.Scanner;

public class HashMenu {

    /**
     * Hash password menu
     */
    public static void hash() throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            displayHashOptions();

            while(!scanner.hasNextInt()){
                System.out.println("Please enter a valid choice.");
                displayHashOptions();
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            int encryptionChoice = scanner.nextInt();

            switch(encryptionChoice){
                case 1:
                    // merge MD5 in this branch first
                    break;
                case 2:
                    // merge SHA256 in this branch first
                    break;
                case 0:
                    System.out.println("Back...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            break;
        }
    }

    /**
     * Display single encryption methods options
     */
    private static void displayHashOptions() {
        System.out.println("\n--- Store Password ---");
        System.out.println("1. Hash with MD5");
        System.out.println("2. Hash with SHA256");
        System.out.println("0. Return to main menu");
    }
}
