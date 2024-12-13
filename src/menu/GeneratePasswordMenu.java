package menu;

import utils.Common;

import java.util.Scanner;

public class GeneratePasswordMenu {
    /**
     * Generate password menu
     */
    public static void generatePassword() {
        Common.printTitle("GENERATE PASSWORD MENU", "Welcome to the GENERATE PASSWORD Menu!");

        Scanner scanner = new Scanner(System.in);
        String seed;
        while(true){
            // Ask user for a seed
            while (true) {
                System.out.print("\u001B[36mEnter a seed : \u001B[0m");
                String seedInput = scanner.nextLine();
                // Validate the password
                if (!seedInput.isEmpty()) {
                    seed = seedInput;
                    break;
                } else {
                    System.out.println("\u001B[31mPlease enter a valid seed.\u001B[0m");
                }
            }

            // Generate a random password
            String password = utils.GeneratePassword.generate(seed);
            System.out.println("\u001B[36mGenerated password: \u001B[0m" + password);

            System.out.print("\u001B[36mPress enter to generate another password or type '0' to return to the main menu: \u001B[0m");
            // Read user input
            String input = new java.util.Scanner(System.in).nextLine();
            if (input.equals("0")) {
                break;
            }
        }
    }
}
