import static encryption.Rot.*;
import java.util.Scanner;
import menu.MainMenu;
import utils.Common;

public class Main {
    // Scanner global pour gérer les entrées utilisateur
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // display welcome message
        displayWelcomeMessage();

        // Attendre que l'utilisateur appuie sur une touche
        Common.promptToContinue(scanner);

        // Lancement du menu principal
        MainMenu.mainMenu();
    }

    public static void displayWelcomeMessage() {
        Common.clearScreen(); // Nettoie la console avant l'affichage

        // Encapsulation avec des #
        String border = "##############################################################";
        System.out.println(border);
        System.out.println(
                "   _____ _  _    _____ _______     _______ _______ \n" +
                        "  / ____| || |  / ____|  __ \\ \\   / /  __ \\__   __|\n" +
                        " | |  __| || |_| |    | |__) \\ \\_/ /| |__) | | |   \n" +
                        " | | |_ |__   _| |    |  _  / \\   / |  ___/  | |   \n" +
                        " | |__| |  | | | |____| | \\ \\  | |  | |      | |   \n" +
                        "  \\_____|  |_|  \\_____|_|  \\_\\ |_|  |_|      |_|   \n" +
                        "                                                   \n"
        );
        System.out.println(border);

        // Sous-titre avec style
        System.out.println("\033[1;34m" + // Texte en bleu gras
                "A simple yet secure password manager\n" +
                "\033[0m"); // Réinitialisation des couleurs
    }
}
