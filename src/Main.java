import static encryption.Rot.*;
import java.util.Scanner;
import menu.MainMenu;
import utils.Common;

public class Main {
    // Scanner global pour gérer les entrées utilisateur
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // display welcome message
        Common.printTitle("G4CRYPT", "A simple yet secure password manager");

        // Attendre que l'utilisateur appuie sur une touche
        Common.promptToContinue(scanner);

        // Lancement du menu principal
        MainMenu.mainMenu();
    }
}
