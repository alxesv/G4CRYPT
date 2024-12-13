package menu;

import utils.Common;
import utils.Lfsr;

import java.math.BigInteger;
import java.util.Scanner;

public class RandomNumberMenu {
    /**
     * Display the random number menu
     */
    public static void randomNumber() {
        Common.printTitle("RANDOM NUMBER MENU", "Welcome to the RANDOM NUMBER Menu!");

        boolean lfsrMenuRunning = true;
        Scanner scanner = new Scanner(System.in);

        // Loop until the user exits the menu
        while (lfsrMenuRunning) {
            int iterations;
            String seed;
            // Ask user for seed and number of iterations
            System.out.print("\u001B[36mEnter seed: \u001B[0m");
            while (!scanner.hasNext("^[a-zA-Z0-9]+$")) {
                System.out.println("\u001B[31mPlease enter a valid seed (alphanumeric characters only)\u001B[0m");
                System.out.print("\u001B[36mEnter seed: \u001B[0m");
                scanner.next();
            }
            seed = scanner.next();


            // Loop until the user enters a valid number of iterations
            System.out.print("\u001B[36mEnter number of iterations (1 to 128): \u001B[0m");
            while (true) {
                if (!scanner.hasNextInt()) {
                    System.out.println("\u001B[31mPlease enter a valid number\u001B[0m");
                    System.out.print("\u001B[36mEnter number of iterations (1 to 128): \u001B[0m");
                    scanner.next();
                } else {
                    iterations = scanner.nextInt();
                    if (iterations >= 1 && iterations <= 128) {
                        // Valid input
                        // Exit the loop
                        break;
                    } else {
                        System.out.println("\u001B[31mPlease enter a number between 1 and 128\u001B[0m");
                        System.out.print("\u001B[36mEnter number of iterations (1 to 128): \u001B[0m");
                    }
                }
            }

            // Generate random numbers
            BigInteger result = Lfsr.run(seed, iterations, java.util.Optional.empty());
            System.out.println("\u001B[36mRandom number: \u001B[0m" + result);

            Scanner scan = new Scanner(System.in);
            Common.promptToContinue(scan);

            // End of the menu
            lfsrMenuRunning = false;
        }
    }
}
