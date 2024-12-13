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
            System.out.println("\n--- Chain Encryption Menu ---");
            System.out.println("Select between " + minChainLength + " and " + maxChainLength + " encryption methods for the chain.");
            System.out.println("Selected methods: " + (selectedMethods.isEmpty() ? "None" : selectedMethods));
            System.out.println("Available options:");

            // Display available options
            for (int i = 0; i < availableOptions.length; i++) {
                if (!selectedMethods.contains(availableOptions[i])) {
                    System.out.println((i + 1) + ". " + availableOptions[i]);
                }
            }

            System.out.print("\nEnter your choice (1-" + availableOptions.length + ", or 0 to finish): ");

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
                System.out.println("Invalid choice. Please select a valid option or 0 to finish.");
                scanner.nextLine();
            }

            // Handle exit case
            if (choice == 0) {
                if (selectedMethods.size() < minChainLength) {
                    System.out.println("You must select at least " + minChainLength + " methods. Continue selecting.");
                } else {
                    break;
                }
            } else {
                // Add the selected method to the chain
                selectedMethods.add(availableOptions[choice - 1]);
                System.out.println(availableOptions[choice - 1] + " added to the chain.");
            }
        }

        List<String> orderedMethods = reorderSelectedMethods(selectedMethods);

        handleSelectedChain(orderedMethods);
    }

    private static void handleSelectedChain(List<String> selectedMethods) throws Exception {
        // Store the method variables for each encryption method
        StringBuilder methodVariables = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        // Handle the selected chain of encryption methods
        System.out.println("Selected chain: " + selectedMethods);

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
                    String vigenereKey = Common.getAlphabetCharactersOnly();
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
                    System.out.println("Invalid method: " + method);
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
                        selectedMethods.contains("POLYBIUS")
        ){
            // Limit to alphabet characters only for ROT, Vigenere, Enigma, and Polybius
            while (true) {
                System.out.print("Enter the password: ");
                String password = scanner.nextLine();
                // Validate the password limit to alphabet characters only
                if (!password.isEmpty() && password.matches("[a-zA-Z]+")) {
                    // Remove all non-alphabetic characters and convert to lowercase
                    return password.replaceAll("[^a-zA-Z]", "").toLowerCase();
                } else {
                    System.out.println("Please enter a valid password (alphanumeric characters only).");
                }
            }
        } else {
            // Allow any characters
            while (true) {
                System.out.print("Enter the password: ");
                String password = scanner.nextLine();
                // Validate the password
                if (!password.isEmpty()) {
                    return password;
                } else {
                    System.out.println("Please enter a valid password.");
                }
            }
        }
    }

    /**
     * Reorder the selected methods to ensure AES, RC4 and Polybius are at the end of the chain
     * because the other algorithms can't use special characters in the password.
     * @param selectedMethods the selected methods
     * @return the reordered methods
     */
    private static List<String> reorderSelectedMethods(List<String> selectedMethods){
        List<String> reorderedMethods = new ArrayList<>(selectedMethods);
        boolean polybius = reorderedMethods.remove("POLYBIUS");
        boolean rc4 = reorderedMethods.remove("RC4");
        boolean aes = reorderedMethods.remove("AES");
        if(polybius){
            reorderedMethods.add("POLYBIUS");
        }
        if(rc4){
            reorderedMethods.add("RC4");
        }
        if(aes){
            reorderedMethods.add("AES");
        }
        return reorderedMethods;
    }
}