package menu;

import encryption.Vigenere;
import menu.HashMenu;

import java.util.Scanner;

public class StoreMenu {
    /**
     * Store password menu
     */
    public static void store() throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display store menu options
            printStoreOptions();

            System.out.print("Enter your choice: ");

            // Validate user input
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid choice.");
                printStoreOptions();
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            int choice = scanner.nextInt();

            // Process user input
            switch (choice) {
                case 1:
                    displaySingleEncryptionsOptions();

                    while(!scanner.hasNextInt()){
                        System.out.println("Please enter a valid choice.");
                        displaySingleEncryptionsOptions();
                        System.out.print("Enter your choice: ");
                        scanner.next();
                    }
                    int encryptionChoice = scanner.nextInt();

                    switch(encryptionChoice){
                        case 1:
                            RotationMenu.rotation();
                            // Remove break and add the rotation encryption menu
                            break;
                        case 2:
                            VigenereMenu.vigenere();
                            break;
                        case 3:
                            PolybiusMenu.polybius();
                            break;
                        case 4:
                            Rc4Menu.RC4();
                            break;
                        case 5:
                            EnigmaMenu.enigma();
                            break;
                        case 6:
                            AesMenu.aes();
                            break;
                        case 7:
                            HashMenu.hash();
                            break;
                        case 0:
                            System.out.println("Back...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case 2:
                    ChainMenu.chain();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    // Exit the help menu
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Display single encryption methods options
     */
    private static void displaySingleEncryptionsOptions() {
        System.out.println("\n--- Store Password ---");
        System.out.println("Please select one of the following encryption methods:");
        System.out.println("1. Rotation (Ceasar)");
        System.out.println("2. Vigenère");
        System.out.println("3. Polybius");
        System.out.println("4. RC4");
        System.out.println("5. Enigma");
        System.out.println("6. AES");
        System.out.println("7. Hash Menu");
        System.out.println("0. Return to main menu");
    }

    /**
     * Print the store options available to the user
     */
    private static void printStoreOptions(){
        System.out.println("\n--- Store Password ---");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Use a single encryption method");
        System.out.println("2. Use a chain of encryption methods");
        System.out.println("0. Return to main menu");
    }
}
