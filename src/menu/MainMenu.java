package menu;

import java.util.Scanner;
import java.util.stream.IntStream;
import menu.HelpMenu;

public class MainMenu {
    /**
     * Main menu of the application
     * Allows user to navigate to different options
     */
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int[] validChoices = {1, 2, 3, 4, 0};
        boolean running = true;

        while (running) {

            printOptions();

            System.out.print("Enter your choice: ");

            // Validate user input
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid choice :");
                printOptions();
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            int choice = scanner.nextInt();
            // Check if the choice is valid
            if (IntStream.of(validChoices).noneMatch(x -> x == choice)) {
                System.out.println("Invalid choice, here are the options :");
                continue;
            }

            // Navigate to the selected option
            switch (choice) {
                case 1:
                    System.out.println("Store");
                    // Remove break and add the storing password menu
                    break;
                case 2:
                    System.out.println("Retrieve");
                    // Remove break and add the retrieving password menu
                    break;
                case 3:
                    System.out.println("Generate");
                    // Remove break and add the generating password menu
                    break;
                case 4:
                    System.out.println("Help");
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
        }
        scanner.close();
    }

    /**
     * Print the options available to the user
     */
    private static void printOptions(){
        System.out.println("1. Store password");
        System.out.println("2. Retrieve password");
        System.out.println("3. Generate secure password");
        System.out.println("4. Help - How to use the application");
        System.out.println("0. Exit");
    }

}
