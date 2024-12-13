package menu;

import utils.RetrieveCSV;
import utils.Common;

import java.util.List;
import java.util.Scanner;

/**
 * DeleteMenu class provides a menu-based interface for deleting passwords from a CSV file.
 */
public class DeleteMenu {

    /**
     * Main method to handle the password deletion menu.
     * Displays passwords stored in a CSV file and allows the user to delete them.
     *
     * @throws Exception If any error occurs during the deletion process.
     */
    public static void delete() throws Exception {
        System.out.println("--- Delete Password Menu ---\n");

        // Retrieve the list of passwords from the CSV file
        List<String> list = RetrieveCSV.getListCSV();

        if (list.isEmpty()) {
            System.out.println("No data found in the CSV file.");
            return;
        }

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            // Display the list of passwords
            System.out.println("Available passwords:");
            for (int i = 0; i < list.size(); i++) {
                String[] parts = list.get(i).split(":");
                String name = parts[0];
                String password = parts[1];
                System.out.println((i + 1) + " - Name: " + name + ", Password: " + password);
            }

            // Prompt the user for input
            System.out.print("\nEnter the corresponding number to delete the password, or '0' to quit: ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                running = false;
            } else {
                try {
                    int index = Integer.parseInt(input) - 1;

                    if (index >= 0 && index < list.size()) {
                        // Confirm deletion
                        System.out.print("Are you sure you want to delete this password? (yes/no): ");
                        String confirmation = scanner.nextLine();

                        if (confirmation.equalsIgnoreCase("yes")) {
                            // Remove the selected entry
                            list.remove(index);

                            // Update the CSV file
                            RetrieveCSV.updateCSV(list);

                            System.out.println("Password deleted successfully.\n");
                        } else {
                            System.out.println("Deletion cancelled.\n");
                        }
                    } else {
                        System.out.println("Invalid number. Please choose a valid index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number or '0' to exit.");
                }
            }
        }
        System.out.println("Exiting...");
    }
}
