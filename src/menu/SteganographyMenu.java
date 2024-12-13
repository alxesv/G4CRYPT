package menu;

import utils.Steganography;

import java.io.File;
import java.io.IOException;
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
                scanner.next(); // Consume invalid input
                System.out.print("Choose an option: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after nextInt()

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

    }

    /**
     * Print the options available for hiding a message
     */
    private static void printOptionsForHiding() {
        System.out.println("1. Hide in an image");
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
                scanner.next(); // Consume invalid input
                System.out.print("Choose an option: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after nextInt()

            switch (choice) {
                case 1:
                    hideMessageInImage(scanner);
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
     * Handle the action for reading a hidden message from an image
     */
    private static void handleReadMessage(Scanner scanner) {
        System.out.print("Enter the path of the image to read the hidden message from: ");
        String imagePath = scanner.nextLine();

        // Check if the file exists
        File imageFile = new File(imagePath);
        if (!imageFile.exists() || !imageFile.isFile()) {
            System.out.println("Error: The file does not exist or is not a valid file.");
            return;
        }

        try {
            String message = Steganography.retrieveTextFromImage(imagePath);
            System.out.println("Hidden Message: " + message);
        } catch (IOException e) {
            System.out.println("Error reading message from image: " + e.getMessage());
        }
    }

    /**
     * Hide a message in an image
     */
    private static void hideMessageInImage(Scanner scanner) {
        System.out.print("Enter the path of the image to hide the message in: ");
        String inputImagePath = scanner.nextLine();

        // Check if the input image exists
        File inputFile = new File(inputImagePath);
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("Error: The input image does not exist or is not a valid file.");
            return;
        }

        System.out.print("Enter the path to save the image with the hidden message: ");
        String outputImagePath = scanner.nextLine();

        // Check if the output path has a valid extension
        int lastDotIndex = outputImagePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex < outputImagePath.lastIndexOf(File.separator)) {
            // No extension found, add ".png"
            outputImagePath += ".png";
        }

        // Check if the output path is valid (directory exists)
        File outputFile = new File(outputImagePath);
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            System.out.println("Error: The output directory does not exist.");
            return;
        }

        System.out.print("Enter the message to hide: ");
        String message = scanner.nextLine();

        try {
            Steganography.hideTextInImage(inputImagePath, outputImagePath, message);
            System.out.println("Message successfully hidden in the image. Saved to: " + outputImagePath);
        } catch (IOException e) {
            System.out.println("Error hiding message in image: " + e.getMessage());
        }
    }
}
