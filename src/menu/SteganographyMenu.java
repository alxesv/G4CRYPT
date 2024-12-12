package menu;

import utils.Steganography;
import java.util.Scanner;

public class SteganographyMenu {
    /**
     * Display the STEGANOGRAPHY menu
     */
    public static void steganography() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("--- STEGANOGRAPHY Encryption ---\n");

            // Ask if the user wants to hide or read a message
            System.out.println("Would you like to:");
            System.out.println("1. Hide a message");
            System.out.println("2. Read a hidden message");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }
            choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting Steganography menu. Goodbye!");
                break;
            }

            switch (choice) {
                case 1:
                    handleHideMessage(scanner);
                    break;
                case 2:
                    handleReadMessage(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }

        } while (choice != 0);

        scanner.close();
    }

    /**
     * Print the options available for hiding a message
     */
    private static void printOptionsForHiding() {
        System.out.println("1. Hide in a picture");
        System.out.println("2. Hide in a Text (released soon)");
        System.out.println("3. Hide in a music (released soon)");
        System.out.println("4. Hide in a video (released soon)");
        System.out.println("0. Go back");
    }

    /**
     * Handle the action for hiding a message
     */
    private static void handleHideMessage(Scanner scanner) {
        int choice;

        do {
            System.out.println("\n--- Hide Message ---");
            printOptionsForHiding();

            System.out.print("Choose an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleHideInPicture(scanner);
                    break;
                case 2:
                    System.out.println("Hide in a Text: Feature coming soon!\n");
                    break;
                case 3:
                    System.out.println("Hide in a music: Feature coming soon!\n");
                    break;
                case 4:
                    System.out.println("Hide in a video: Feature coming soon!\n");
                    break;
                case 0:
                    System.out.println("Going back...\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }

        } while (choice != 0);
    }

    /**
     * Handle the action for reading a hidden message
     */
    private static void handleReadMessage(Scanner scanner) {
        System.out.print("Enter the image path to extract the hidden message: ");
        scanner.nextLine(); // Consume the newline character
        String inputImagePath = scanner.nextLine();

        try {
            String hiddenMessage = Steganography.retrieveTextFromImage(inputImagePath);
            System.out.println("Hidden message: " + hiddenMessage + "\n");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handle the action for hiding a message in a picture
     */
    private static void handleHideInPicture(Scanner scanner) {
        try {
            scanner.nextLine(); // Consume the newline
            System.out.print("Enter the input image path: ");
            String inputImagePath = scanner.nextLine();

            System.out.print("Enter the output image path: ");
            String outputImagePath = scanner.nextLine();

            System.out.print("Enter the message to hide: ");
            String message = scanner.nextLine();

            Steganography.hideTextInImage(inputImagePath, outputImagePath, message);

            System.out.println("Message hidden successfully in the image!\n");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage() + "\n");
        }
    }
}
