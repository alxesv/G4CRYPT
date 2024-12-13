package menu;

import utils.Common;

import java.util.Scanner;

public class EasterEggMenu {

    /**
     * Display the secret Easter Egg message
     */
    static void displaySecretMessage() throws Exception {
        // Title in blue
        System.out.println("\u001B[1;34m==================== EASTER EGG ====================\u001B[0m");

        // Message in yellow
        System.out.println("\u001B[32m🎉 Congrats, you've unlocked the Easter Egg!\u001B[0m");

        // Closing decoration in blue
        System.out.println("\u001B[1;34m====================================================\u001B[0m");

        // Funny message in green
        System.out.println("\u001B[32mBut seriously... 'Crocodile22!'? That's a password a crocodile could guess!\u001B[0m");

        // Security advice in red
        System.out.println("\u001B[32mTime to upgrade your security, my friend!\u001B[0m");

        Scanner scanner = new Scanner(System.in);

        Common.promptToContinue(scanner);

        MainMenu.mainMenu();

    }
}