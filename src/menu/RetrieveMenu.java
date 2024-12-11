package menu;

import utils.Common;
import utils.RetrieveCSV;

import java.util.List;
import java.util.Scanner;

public class RetrieveMenu {
    /**
     * Display the retrieve menu
     */
    public static void retrieve() {
        System.out.println("--- Retrieve Password Menu ---\n");
        List<String> list = RetrieveCSV.getListCSV();

        if (list.isEmpty()) {
            System.out.println("No data found in the CSV file.");
            return;
        }

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            // Affiche les données récupérées
            System.out.println("Available passwords:");
            for (int i = 0; i < list.size(); i++) {
                String[] parts = list.get(i).split(":");
                String name = parts[0];
                String password = parts[1];
                System.out.println(i+1 + " - Name: " + name + ", Password: " + password);
            }

            System.out.print("\nEnter the corresponding number to retrieve the password, or '0' to quit: ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                running = false;
            } else {
                try {
                    int index = Integer.parseInt(input)-1;

                    if (index >= 0 && index < list.size()) {
                        String[] parts = list.get(index).split(":");
                        String name = parts[0];
                        String encryptedPassword = parts[1];
                        String method = parts[2];
                        String args = parts.length > 3 ? parts[3] : null;

                        String decryptedPassword = decryptPassword(encryptedPassword, method, args);

                        System.out.println("\nName: " + name);
                        System.out.println("Decrypted Password: " + decryptedPassword+"\n");
                    } else {
                        System.out.println("Invalid number. Please choose a valid index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number or 'exit'.");
                }
            }
        }

        System.out.println("Exiting...");
    }

    /**
     * Déchiffre le mot de passe en fonction de la méthode et des arguments.
     */
    public static String decryptPassword(String encryptedPassword, String method, String args) {
        switch (method.toUpperCase()) {
            case "ROT":
                int shift = args != null ? Integer.parseInt(args) : 0;
                // TODO: call decryptROT function
                System.out.println("decrypt ROT...");
                return "TODO";
            default:
                return "Unsupported decryption method: " + method;
        }
    }
}
