package menu;

import utils.Lfsr;

import java.math.BigInteger;
import java.util.Scanner;

public class RandomNumberMenu {
    /**
     * Display the random number menu
     */
    public static void randomNumber() {
        boolean lfsrMenuRunning = true;
        Scanner scanner = new Scanner(System.in);

        // Loop until the user exits the menu
        while (lfsrMenuRunning) {
            int iterations;
            String seed;
            // Ask user for seed and number of iterations
            System.out.print("Enter seed: ");
            while (!scanner.hasNext("^[a-zA-Z0-9]+$")) {
                System.out.println("Please enter a valid seed (alphanumeric characters only)");
                System.out.print("Enter seed: ");
                scanner.next();
            }
            seed = scanner.next();


            // Loop until the user enters a valid number of iterations
            System.out.print("Enter number of iterations (1 to 128): ");
            while (true) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number");
                    System.out.print("Enter number of iterations (1 to 128): ");
                    scanner.next();
                } else {
                    iterations = scanner.nextInt();
                    if (iterations >= 1 && iterations <= 128) {
                        // Valid input
                        // Exit the loop
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 128");
                        System.out.print("Enter number of iterations (1 to 128): ");
                    }
                }
            }

            // Generate random numbers
            BigInteger result = Lfsr.run(seed, iterations, java.util.Optional.empty());
            System.out.println("Random number: " + result);
            // End of the menu
            lfsrMenuRunning = false;
        }
    }
}
