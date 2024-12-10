package menu;

import java.util.Scanner;

public class HelpMenu {
    /**
     * Help information menu
     */
    public static void help() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            int wrongInputCounter = 0;
            // Display help menu options
            printHelpOptions();

            System.out.print("Enter your choice: ");

            // Validate user input
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid choice.");
                printHelpOptions();
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            int choice = scanner.nextInt();

            // Process user input
            switch (choice) {
                case 1:
                    displayStorePasswordHelp();
                    hold();
                    break;
                case 2:
                    displayRetrievePasswordHelp();
                    hold();
                    break;
                case 3:
                    displayGeneratePasswordHelp();
                    hold();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    running = false; // Exit the help menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Display help information for storing a password
     */
    private static void displayStorePasswordHelp() {
        System.out.println("\n--- Store Password ---");
        System.out.println("To store a password, you need to provide the following information:");
        System.out.println("a. Name of the service");
        System.out.println("b. The password");
        System.out.println("You will then get the choice between different encryption methods:");
        System.out.println("- Vigenere: Each letter of the password is shifted using a keyword, invented by Blaise de Vigenère.");
        System.out.println("- Rotation: Each letter of the password is shifted by a fixed number, used by Caesar.");
        System.out.println("- Polybe: Each letter of the password is substituted using an alphabet square, used by ancient Greeks.");
        System.out.println("- Enigma: Encryption using a series of three rotors and a reflector, used by the Nazis in World War II.");
        System.out.println("- RC4: Encrypts the password bit by bit using a key, used in the early 2000s for web security.");
        System.out.println();
    }

    /**
     * Display help information for retrieving a password
     */
    private static void displayRetrievePasswordHelp() {
        System.out.println("\n--- Retrieve Password ---");
        System.out.println("Retrieve passwords that have been securely stored. You will need to:");
        System.out.println("- Provide the service name for which the password was stored.");
        System.out.println();
    }

    /**
     * Display help information for generating a secure password
     */
    private static void displayGeneratePasswordHelp() {
        System.out.println("\n--- Generate Secure Password ---");
        System.out.println("This option allows you to create a strong password");
        System.out.println("- Receive a randomly generated secure password.");
        System.out.println();
    }

    /**
     * Print the help menu options
     */
    private static void printHelpOptions() {
        System.out.println("\n--- Help Menu ---");
        System.out.println("1. Store Password");
        System.out.println("2. Retrieve Password");
        System.out.println("3. Generate Secure Password");
        System.out.println("0. Return to Main Menu");
    }

    /**
     * Hold the program until the user presses Enter
     */
    private static void hold(){
        System.out.println("Press Enter to go back to the help menu...");
        new Scanner(System.in).nextLine();
    }
}
