package menu;

import encryption.Aes;
import encryption.Rc4;
import utils.GeneratePassword;
import utils.Lfsr;

import java.util.Scanner;
import java.util.stream.IntStream;

public class MainMenu {
    /**
     * Main menu of the application
     * Allows user to navigate to different options
     */
    public static void mainMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int[] validChoices = {1, 2, 3, 4, 5, 0};
        boolean running = true;

        while (running) {

            printOptions();

            System.out.print("Enter your choice: ");

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
                    System.out.println("Please enter a valid choice or the Easter Egg:");
                    printOptions();
                    System.out.print("Enter your choice: ");
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
                      System.out.println("Exiting");
                      // Exit the loop
                      running = false;
                      break;
                  default:
                      System.out.println("Invalid choice");
                      break;
                }
            } else {
                System.out.println("Invalid choice, here are the options:");
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
     * Print the options available to the user
     */
    private static void printOptions() {
        System.out.println("1. Store password");
        System.out.println("2. Retrieve password");
        System.out.println("3. Hash password");
        System.out.println("4. Generate secure password");
        System.out.println("5. Generate random numbers");
        System.out.println("6. Help - How to use the application");
        System.out.println("0. Exit");
    }

    private static boolean isEasterEgg(String text) {
        String password = "Crocodile22";
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
