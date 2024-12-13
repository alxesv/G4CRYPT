package menu;

import encryption.Aes;
import encryption.Chain;
import encryption.Polybius;
import utils.AesKeyManager;
import utils.Common;
import utils.SaveData;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import static menu.EnigmaMenu.getPositiveInt;

public class ChainMenu {

    /**
     * Display the chain menu and allow the user to select a chain of encryption methods.
     */
    public static void chain() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] availableOptions = {"ROT", "VIGENERE", "ENIGMA", "POLYBIUS", "RC4", "AES"};
        List<String> selectedMethods = new ArrayList<>();
        int maxChainLength = availableOptions.length; // Maximum chain length is the number of available options
        int minChainLength = 2; // Minimum chain length

        while (selectedMethods.size() < maxChainLength) {
            Common.clearScreen();

            // Display the chain encryption menu with title
            System.out.print("\n");
            printChainMenuTitle();

            System.out.println("\u001B[36mSelect between " + minChainLength + " and " + maxChainLength + " encryption methods for the chain.  \u001B[0m");
            System.out.println("\u001B[36mSelected methods: \u001B[0m" + (selectedMethods.isEmpty() ? "None" : selectedMethods));
            System.out.println("\n"+"\u001B[36mAvailable options:  \u001B[0m");

            // Display available options
            for (int i = 0; i < availableOptions.length; i++) {
                if (!selectedMethods.contains(availableOptions[i])) {
                    System.out.println("\u001B[36m"+(i + 1) + ".   \u001B[0m" + availableOptions[i]);
                }
            }

            System.out.print("\u001B[36mEnter your choice (1-" + availableOptions.length + ", or 0 to finish):  \u001B[0m");

            int choice;
            // Check if input is an integer and within the valid range
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice == 0 || (choice >= 1 && choice <= availableOptions.length &&
                            !selectedMethods.contains(availableOptions[choice - 1]))) {
                        break;
                    }
                }
                System.out.print("\u001B[31mInvalid choice.\u001B[0m \u001B[36mPlease select a valid option or 0 to finish: \u001B[0m");
                scanner.nextLine();
            }

            // Handle exit case
            if (choice == 0) {
                if (selectedMethods.size() < minChainLength) {
                    System.out.print("\u001B[33mYou must select at least " + minChainLength + " methods. Continue selecting.\u001B[0m ");
                } else {
                    break;
                }
            } else {
                // Add the selected method to the chain
                selectedMethods.add(availableOptions[choice - 1]);
                System.out.println("\u001B[1;32m"+availableOptions[choice - 1] + " added to the chain. \u001B[0m");
            }
        }

        List<String> orderedMethods = reorderSelectedMethods(selectedMethods);

        handleSelectedChain(orderedMethods);
    }

    private static void printChainMenuTitle() {
        // Display title and separator for the Chain Encryption menu
        System.out.println("\u001B[1;34m==================== CHAIN ENCRYPTION MENU ====================\u001B[0m");
        System.out.println("\u001B[1;32m   Welcome to the Chain Encryption Method Selection!         \u001B[0m");
        System.out.println("\u001B[1;34m==============================================================\u001B[0m");
    }

    private static void handleSelectedChain(List<String> selectedMethods) throws Exception {
        // Store the method variables for each encryption method
        StringBuilder methodVariables = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        // Handle the selected chain of encryption methods
        System.out.println("\u001B[36mSelected chain: \u001B[0m" + selectedMethods);

        // Ask name of the service
        String service = Common.getServiceName();

        // Ask for the password
        String password = handlePasswordSelection(selectedMethods);

        for (String method : selectedMethods) {
            switch (method) {
                case "ROT":
                    // Ask for the rotation value
                    System.out.println("Select the rotation value for the ROT method:");
                    int rotationValue = RotationMenu.getRotateKey();
                    methodVariables.append("ROT").append(">").append(rotationValue).append(";");
                    break;
                case "VIGENERE":
                    // Ask for the Vigenere key
                    System.out.println("Select a key for the Vigenere method:");
                    String vigenereKey = VigenereMenu.getVigenereKey();
                    methodVariables.append("VIGENERE").append(">").append(vigenereKey).append(";");
                    break;
                case "ENIGMA":
                    System.out.println("Select the Enigma rotor positions:");
                    // Ask for the rotor positions
                    int rotor1, rotor2, rotor3;
                    System.out.print("Do you want to choose the rotor positions? (yes/no): ");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (choice.equals("yes") || choice.equals("y")) {
                        // Let the user input rotor positions
                        rotor1 = getPositiveInt(scanner, "Enter position for rotor 1 (0-25): ");
                        rotor2 = getPositiveInt(scanner, "Enter position for rotor 2 (0-25): ");
                        rotor3 = getPositiveInt(scanner, "Enter position for rotor 3 (0-25): ");
                    } else {
                        // Generate random rotor positions
                        rotor1 = new java.util.Random().nextInt(26);
                        rotor2 = new java.util.Random().nextInt(26);
                        rotor3 = new java.util.Random().nextInt(26);

                        System.out.println("Random rotor positions selected: Rotor 1: " + rotor1 + ", Rotor 2: " + rotor2 + ", Rotor 3: " + rotor3);
                    }
                    String rotorPositions = rotor1 + "," + rotor2 + "," + rotor3;
                    methodVariables.append("ENIGMA").append(">").append(rotorPositions).append(";");
                    break;
                case "RC4":
                    System.out.println("Select the seed for the RC4 method:");
                    // Ask for a seed
                    String rc4Seed = Rc4Menu.getSeed();
                    methodVariables.append("RC4").append(">").append(rc4Seed).append(";");
                    break;
                case "AES":
                    System.out.println("Generating an AES key...");
                    // Generate an AES key
                    SecretKey secretKey = Aes.generateKey();
                    String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
                    methodVariables.append("AES").append(">").append(encodedSecretKey).append(";");
                    break;
                case "POLYBIUS":
                    System.out.println("Generating a Polybius square...");
                    // Generate a Polybius square
                    char[][] grid = Polybius.generatePolybiusGrid();
                    String gridString = Polybius.gridToString(grid);
                    methodVariables.append("POLYBIUS").append(">").append(gridString).append(";");
                    break;
                default:
                    System.out.println("\u001B[31mInvalid method: \u001B[0m" + method);
            }
        }
        // Call the chain encryption method
        String encryptedPassword = Chain.handleChainEncryption(password, methodVariables.toString());

        //Retrieve AES Key for saving the data
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        SaveData.saveData(service, encryptedPassword, "CHAIN", methodVariables.toString(), aesKey);
    }

    private static String handlePasswordSelection(List<String> selectedMethods){
        Scanner scanner = new Scanner(System.in);
        if(
                selectedMethods.contains("ROT") ||
                        selectedMethods.contains("VIGENERE") ||
                        selectedMethods.contains("ENIGMA") ||
                        selectedMethods.contains("RC4") ||
                        selectedMethods.contains("AES") ||
                        selectedMethods.contains("POLYBIUS")
        ){
            System.out.print("\u001B[36mEnter the password for encryption: \u001B[0m");
            return scanner.nextLine();
        } else {
            return "\u001B[31mNo password\u001B[0m";
        }
    }

    private static List<String> reorderSelectedMethods(List<String> selectedMethods) {
        // Reorder selected methods to a fixed sequence based on priority
        List<String> orderedMethods = new ArrayList<>();
        orderedMethods.addAll(selectedMethods);
        return orderedMethods;
    }
}
