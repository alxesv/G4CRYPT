package menu;

import encryption.Aes;
import encryption.Polybius;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PolybiusMenu {
    /**
     * Display the Polybius menu
     */
    public static void polybius() throws Exception {

        Common.printTitle("POLYBIUS MENU", "Welcome to the POLYBIUS encryption Menu!");

        // Inform the user about the encryption behavior
        System.out.println("\u001B[36mNote: Only letters will be encrypted. Spaces, numbers, and special characters will be removed.\u001B[0m");
        System.out.println("\u001B[36mThe text will also be converted to uppercase before encryption.\n\u001B[0m");

        // Ask the name of the service
        String service = Common.getServiceName();

        // Ask the password to encrypt
        String password = Common.getAlphabetCharactersOnly();

        // Generate and display the Polybius grid
        char[][] grid = Polybius.generatePolybiusGrid();
        System.out.println("\u001B[36mPolybius Grid:\u001B[0m");
        displayGrid(grid);

        // Encrypt the password using Polybius
        String encryptedPassword = Polybius.encrypt(password, grid);
        System.out.println("\u001B[36mEncrypted Password: \u001B[0m" + encryptedPassword);

        //Retrieve AES Key
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        // Save the encrypted data
        String gridAsString = Polybius.gridToString(grid);
        SaveData.saveData(service, encryptedPassword, "POLYBIUS", gridAsString, aesKey);
    }

    /**
     * Display the Polybius grid
     * @param grid the Polybius grid
     */
    private static void displayGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}