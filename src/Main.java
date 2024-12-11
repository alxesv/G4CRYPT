import static encryption.Rot.*;
import java.util.Scanner;
import java.util.stream.IntStream;
import menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        // Print the welcome message
        System.out.println("Welcome to G4CRYPT");
        System.out.println("A simple password manager");

        System.out.println(encryptRot("hello", -4));
        System.out.println(decryptRot());

        // Start the main menu
        MainMenu.mainMenu();
    }
}