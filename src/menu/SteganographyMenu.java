package menu;

import utils.Common;
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
            Common.printTitle("STEGANOGRAPHY MENU", "Welcome to the STEGANOGRAPHY menu!");

            // Ask if the user wants to hide or read a message
            System.out.println("\u001B[36mWould you like to:\u001B[0m");
            System.out.println("\u001B[36m1.\u001B[0m Hide a message");
            System.out.println("\u001B[36m2.\u001B[0m Read a hidden message");
            System.out.println("\u001B[31m0.\u001B[0m Exit");

            System.out.print("\u001B[36mChoose an option: \u001B[0m");
            while (!scanner.hasNextInt()) {
                System.out.println("\u001B[31mInvalid input. Please enter a number.\u001B[0m");
                scanner.next(); // Consume invalid input
                System.out.print("\u001B[36mChoose an option: \u001B[0m");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after nextInt()

            if (choice == 0) {
                System.out.println("\u001B[36mExiting Steganography menu. Goodbye!\u001B[0m");
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
                    System.out.println("\u001B[31mInvalid choice. Please try again.\n\u001B[0m");
            }

        } while (choice != 0);

    }

    /**
     * Print the options available for hiding a message
     */
    private static void printOptionsForHiding() {
        System.out.println("\u001B[36m1.\u001B[0m Hide in an image");
        System.out.println("\u001B[36m2.\u001B[0m Hide in a Text (released soon)");
        System.out.println("\u001B[36m3.\u001B[0m Hide in a music (released soon)");
        System.out.println("\u001B[36m4.\u001B[0m Hide in a video (released soon)");
        System.out.println("\u001B[31m0.\u001B[0m Go back");
    }

    /**
     * Handle the action for hiding a message
     */
    private static void handleHideMessage(Scanner scanner) {
        int choice;

        do {
            Common.printTitle("HIDE MESSAGE", "Here you can hide a message in a picture ;)");
            printOptionsForHiding();

            System.out.print("\u001B[36mChoose an option: \u001B[0m");
            while (!scanner.hasNextInt()) {
                System.out.println("\u001B[31mInvalid input. Please enter a number.\u001B[0m");
                scanner.next(); // Consume invalid input
                System.out.print("\u001B[36mChoose an option: \u001B[0m");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after nextInt()

            switch (choice) {
                case 1:
                    hideMessageInImage(scanner);
                    break;
                case 2:
                    System.out.println("\u001B[36mHide in a Text: Feature coming soon!\n\u001B[0m");
                    break;
                case 3:
                    System.out.println("\u001B[36mHide in a music: Feature coming soon!\n\u001B[0m");
                    break;
                case 4:
                    System.out.println("\u001B[36mHide in a video: Feature coming soon!\n\u001B[0m");
                    break;
                case 0:
                    System.out.println("\u001B[36mGoing back...\n\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\n\u001B[0m");
            }

        } while (choice != 0);
    }

    /**
     * Handle the action for reading a hidden message from an image
     */
    private static void handleReadMessage(Scanner scanner) {
        System.out.print("\u001B[36mEnter the path of the image to read the hidden message from: \u001B[0m");
        String imagePath = scanner.nextLine();

        // Check if the file exists
        File imageFile = new File(imagePath);
        if (!imageFile.exists() || !imageFile.isFile()) {
            System.out.println("\u001B[31mError: The file does not exist or is not a valid file.\u001B[0m");
            return;
        }

        try {
            String message = Steganography.retrieveTextFromImage(imagePath);
            System.out.println("\u001B[36mHidden Message: \u001B[0m" + message);
        } catch (IOException e) {
            System.out.println("\u001B[31mError reading message from image: \u001B[0m" + e.getMessage());
        }
    }

    /**
     * Hide a message in an image
     */
    private static void hideMessageInImage(Scanner scanner) {
        System.out.print("\u001B[36mEnter the path of the image to hide the message in: \u001B[0m");
        String inputImagePath = scanner.nextLine();

        // Check if the input image exists
        File inputFile = new File(inputImagePath);
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("\u001B[31mError: The input image does not exist or is not a valid file.\u001B[0m");
            return;
        }

        System.out.print("\u001B[36mEnter the path to save the image with the hidden message: \u001B[0m");
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
            System.out.println("\u001B[31mError: The output directory does not exist.\u001B[0m");
            return;
        }

        System.out.print("\u001B[36mEnter the message to hide: \u001B[0m");
        String message = scanner.nextLine();

        try {
            Steganography.hideTextInImage(inputImagePath, outputImagePath, message);
            System.out.println("\u001B[36mMessage successfully hidden in the image. Saved to: \u001B[0m" + outputImagePath);
        } catch (IOException e) {
            System.out.println("\u001B[31mError hiding message in image: \u001B[0m" + e.getMessage());
        }
    }
}
