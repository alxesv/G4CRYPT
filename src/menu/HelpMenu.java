package menu;

import utils.Common;

import java.util.Scanner;

public class HelpMenu {
    /**
     * Help information menu
     */
    public static void help() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
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
                    displayHashMenuHelp();
                    hold();
                    break;
                case 4:
                    displayGeneratePasswordHelp();
                    hold();
                    break;
                case 5:
                    displayGenerateRandomNumbersHelp();
                    hold();
                    break;
                case 6:
                    displaySteganographyHelp();
                    hold();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    // Exit the help menu
                    running = false;
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
        System.out.println("Retrieve passwords that have been securely stored.");
        System.out.println("You will have to select the password you want to retrieve from the list.");
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
     * Display help information for generating random numbers
     */
    private static void displayGenerateRandomNumbersHelp() {
        System.out.println("\n--- Generate Random Numbers ---");
        System.out.println("This option allows you to generate random numbers using an LFSR algorithm.");
        System.out.println("You will need to provide a seed and the number of iterations to generate the random numbers.");
        System.out.println("The seed can be any alphanumeric character and the number of iterations must be between 1 and 128.");
        System.out.println();
    }

    /**
     * Print the help menu options
     */
    private static void printHelpOptions() {
        Common.printTitle("HELP MENU", "Welcome to the HELP Menu!");

        System.out.println("\u001B[1;36m1.\u001B[0m Store Password");
        System.out.println("\u001B[1;36m2.\u001B[0m Retrieve Password");
        System.out.println("\u001B[1;36m3.\u001B[0m Hash menu");
        System.out.println("\u001B[1;36m4.\u001B[0m Generate Secure Password");
        System.out.println("\u001B[1;36m5.\u001B[0m Generate Random Numbers");
        System.out.println("\u001B[1;36m6.\u001B[0m Steganography");
        System.out.println("\u001B[1;36m0.\u001B[0m Return to Main Menu");
    }

    /**
     * Display help information for the hash menu
     */
    private static void displayHashMenuHelp() {
        System.out.println("\n--- Hash Menu ---");
        System.out.println("This option allows you to hash a password using different algorithms.");
        System.out.println("You will need to provide the password to hash and select the algorithm to use.");
        System.out.println("The available algorithms are MD5, SHA-256, and HMAC.");
        System.out.println("You will then be able to compare the stored hash with your password.");
        System.out.println();
    }

    /**
     * Display help information for steganography
     */
    private static void displaySteganographyHelp() {
        System.out.println("\n--- Steganography ---");
        System.out.println("This option allows you to hide a message within an image.");
        System.out.println("You will need to provide the path to the image and the message to hide and the path to the output image.");
        System.out.println("The message will be hidden in the least significant bits of the image pixels.");
        System.out.println("You will then be able to extract the message from the image.");
        System.out.println();
    }

    /**
     * Hold the program until the user presses Enter
     * This is to allow the user to read the information before returning to the help menu
     */
    private static void hold(){
        System.out.println("Press Enter to go back to the help menu...");
        new Scanner(System.in).nextLine();
    }
}
