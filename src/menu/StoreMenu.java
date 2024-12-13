package menu;

import encryption.Vigenere;
import menu.HashMenu;
import utils.Common;

import java.util.Scanner;

public class StoreMenu {
    /**
     * Store password menu
     */
    public static void store() throws Exception {
        Common.clearScreen();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display store menu options with title and decorations
            printTitleAndStoreOptions();

            System.out.print("\u001B[36mEnter your choice: \u001B[0m");

            // Validate user input
            while (!scanner.hasNextInt()) {
                System.out.println("\u001B[31mPlease enter a valid choice.\u001B[0m");
                printStoreOptions();
                System.out.print("\u001B[36mEnter your choice: \u001B[0m");
                scanner.next();
            }

            int choice = scanner.nextInt();

            // Process user input
            switch (choice) {
                case 1:
                    Common.clearScreen();

                    displaySingleEncryptionsOptions();
                    System.out.print("\u001B[36mEnter your choice: \u001B[0m");

                    while(!scanner.hasNextInt()){
                        System.out.println("\u001B[31mPlease enter a valid choice.\u001B[0m");
                        displaySingleEncryptionsOptions();
                        System.out.print("\u001B[36mEnter your choice: \u001B[0m");
                        scanner.next();
                    }
                    int encryptionChoice = scanner.nextInt();

                    switch(encryptionChoice){
                        case 1:
                            RotationMenu.rotation();
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
                        case 0:
                            System.out.println("\u001B[33mBack...\u001B[0m");
                            break;
                        default:
                            System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
                    }
                    break;

                case 2:
                    ChainMenu.chain();
                    break;
                case 0:
                    System.out.println("\u001B[33mReturning to main menu...\u001B[0m");
                    // Exit the help menu
                    running = false;
                    break;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
            }
        }
    }

    /**
     * Display single encryption methods options
     */
    private static void displaySingleEncryptionsOptions() {
        // Add a title for this menu
        printEncryptionTitle();

        System.out.println("\u001B[1;36mPlease select one of the following encryption methods:\u001B[0m");
        System.out.println("\u001B[1;36m1.\u001B[0m Rotation (Ceasar)");
        System.out.println("\u001B[1;36m2.\u001B[0m Vigenère");
        System.out.println("\u001B[1;36m3.\u001B[0m Polybius");
        System.out.println("\u001B[1;36m4.\u001B[0m RC4");
        System.out.println("\u001B[1;36m5.\u001B[0m Enigma");
        System.out.println("\u001B[1;36m6.\u001B[0m AES");
        System.out.println("\u001B[1;31m0.\u001B[0m Return to main menu");
    }

    /**
     * Print the encryption menu title
     */
    private static void printEncryptionTitle() {
        System.out.println("\u001B[1;34m==================== ENCRYPTION METHODS ====================\u001B[0m");
        System.out.println("\u001B[1;32m        Welcome to the Encryption Methods Menu!            \u001B[0m");
        System.out.println("\u001B[1;34m============================================================\u001B[0m");
    }

    /**
     * Print the store options available to the user
     */
    private static void printStoreOptions(){
        System.out.println("\n\u001B[1;34m--- Store Password ---\u001B[0m");
        System.out.println("\u001B[1;36mPlease select one of the following options:\u001B[0m");
        System.out.println("\u001B[1;36m1.\u001B[0m Use a single encryption method");
        System.out.println("\u001B[1;36m2.\u001B[0m Use a chain of encryption methods");
        System.out.println("\u001B[1;31m0.\u001B[0m Return to main menu");
    }

    private static void printTitleAndStoreOptions() {
        System.out.println("\u001B[1;34m==================== STORE MENU ====================\u001B[0m");
        System.out.println("\u001B[1;32m        Welcome to the Password Store Menu!         \u001B[0m");
        System.out.println("\u001B[1;34m====================================================\u001B[0m");
        printStoreOptions();
    }
}
