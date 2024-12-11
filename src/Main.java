import static encryption.Rot.*;
import java.util.Scanner;
import java.util.stream.IntStream;
import menu.MainMenu;

public class Main {
    public static void main(String[] args) throws Exception {
        // Print the welcome message
        System.out.println("Welcome to G4CRYPT");
        System.out.println("A simple password manager");

        // Start the main menu
        MainMenu.mainMenu();
    }
}
