package menu;

import java.util.Scanner;

public class HelpMenu {
    /**
     * Help information
     */
    public static void help() {
        System.out.println("Help information :");
        // Store password information
        System.out.println("1 --- Store password ---");
        System.out.println("To store a password, you need to provide the following information :");
        System.out.println("a. Name of the service");
        System.out.println("b. The password");
        System.out.println("You will then get the choice between different encryption methods");
        System.out.println("Vigenere, Rotation, RC4, Enigma, Polybe\n");
        System.out.println("Vigenere : Each letter of the password is shifted using a keyword, invented by Blaise de Vigenère\n");
        System.out.println("Rotation : Each letter of the password is shifted by a fixed number, used by Caesar\n");
        System.out.println("Polybe : Each letter of the password is substituted using an alphabet square, used by ancient Greeks\n");
        System.out.println("Enigma : Encryption using a series of three rotors and a reflector, used by the nazis in World War 2\n");
        System.out.println("RC4 : A method which encrypts the password bit by bit using a key, used in the early 2000s for web security\n");
        // Retrieve password information
        System.out.println("2 --- Retrieve password ---");
        // todo
        // Generate password information
        System.out.println("3 --- Generate secure password ---");
        // todo

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress enter to go back to the main menu");
        if (scanner.hasNextLine()) {
            MainMenu.mainMenu();
        }
    }
}
