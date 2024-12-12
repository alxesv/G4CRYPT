package menu;

import java.util.Scanner;

public class GeneratePasswordMenu {
    /**
     * Generate password menu
     */
    public static void generatePassword() {
        Scanner scanner = new Scanner(System.in);
        String seed;
        while(true){
            // Ask user for a seed
            while (true) {
                System.out.print("Enter a seed : ");
                String seedInput = scanner.nextLine();
                // Validate the password
                if (!seedInput.isEmpty()) {
                    seed = seedInput;
                    break;
                } else {
                    System.out.println("Please enter a valid seed.");
                }
            }

            // Generate a random password
            String password = utils.GeneratePassword.generate(seed);
            System.out.println("Generated password: " + password);

            System.out.println("Press enter to generate another password or type '0' to return to the main menu.");
            // Read user input
            String input = new java.util.Scanner(System.in).nextLine();
            if (input.equals("0")) {
                break;
            }
        }
    }
}
