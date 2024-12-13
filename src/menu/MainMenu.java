package menu;

import encryption.Rc4;
import utils.Common;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MainMenu {
    /**
     * Main menu of the application
     * Allows user to navigate to different options
     */
    public static void mainMenu() throws Exception {
        Common.clearScreen();

        Scanner scanner = new Scanner(System.in);
        int[] validChoices = {1, 2, 3, 4, 5, 0};
        boolean running = true;

        while (running) {
            // Call function to print the title and decorated menu
            printTitleAndOptions();

            System.out.print("\u001B[36mEnter your choice: \u001B[0m");

            int choice = -1;  // Declare choice before the loop

            while (true) {
                String userInput = scanner.next();

                if (isEasterEgg(userInput)) {
                    EasterEggMenu.displaySecretMessage();
                    break; // Sortir de la boucle après avoir activé l'Easter Egg
                } else if (isInteger(userInput)) {
                    choice = Integer.parseInt(userInput);  // Récupérer le choix valide
                    break; // Sortir de la boucle si un choix valide est donné
                } else {
                    System.out.println("\u001B[31mPlease enter a valid choice or the Easter Egg:\u001B[0m");
                    printOptions();
                    System.out.print("\u001B[36mEnter your choice: \u001B[0m");
                }
            }

            // Check if the choice is valid
            if (isChoiceValid(choice, validChoices)) {
                // Navigate to the selected option
                switch (choice) {
                    case 1:
                        StoreMenu.store();
                        break;
                    case 2:
                        RetrieveMenu.retrieve();
                        break;
                    case 3:
                        HashMenu.hash();
                        break;
                    case 4:
                        GeneratePasswordMenu.generatePassword();
                        break;
                    case 5:
                        RandomNumberMenu.randomNumber();
                        break;
                    case 6:
                        // Display help menu
                        HelpMenu.help();
                        break;
                    case 0:
                        System.out.println("\u001B[33mExiting...\u001B[0m");
                        // Exit the loop
                        running = false;
                        break;
                    default:
                        System.out.println("\u001B[31mInvalid choice, here are the options:\u001B[0m");
                        break;
                }
            } else {
                System.out.println("\u001B[31mInvalid choice, here are the options:\u001B[0m");
            }
        }
        scanner.close();
    }

    /**
     * Check if the user's choice is valid
     *
     * @param choice - the user's choice
     * @param validChoices - the list of valid choices
     * @return true if the choice is valid, otherwise false
     */
    private static boolean isChoiceValid(int choice, int[] validChoices) {
        return IntStream.of(validChoices).anyMatch(x -> x == choice);
    }

    /**
     * Print the title and options available to the user
     */
    private static void printTitleAndOptions() {
        System.out.println("\u001B[1;34m==================== MAIN MENU ====================\u001B[0m");
        System.out.println("\u001B[1;32m            Welcome to the Secure App!              \u001B[0m");
        System.out.println("\u001B[1;34m====================================================\u001B[0m");
        printOptions();
    }

    private static void printOptions() {
        System.out.println("\n\u001B[1;36m1.\u001B[0m Store password");
        System.out.println("\u001B[1;36m2.\u001B[0m Retrieve password");
        System.out.println("\u001B[1;36m3.\u001B[0m Hash password");
        System.out.println("\u001B[1;36m4.\u001B[0m Generate secure password");
        System.out.println("\u001B[1;36m5.\u001B[0m Generate random numbers");
        System.out.println("\u001B[1;36m6.\u001B[0m Help - How to use the application");
        System.out.println("\u001B[1;31m0.\u001B[0m Exit");
    }

    private static boolean isEasterEgg(String text) {
        String password = "Crocodile22!";
        String key = "clementleboss";
        return text.equals(Rc4.encrypt(password, key));
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
