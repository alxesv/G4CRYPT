package menu;

import utils.Common;

import java.util.Scanner;

public class HashMenu {

    /**
     * Hash password menu
     */
    public static void hash() throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            displayHashOptions();

            while(!scanner.hasNextInt()){
                System.out.println("Please enter a valid choice.");
                displayHashOptions();
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            int encryptionChoice = scanner.nextInt();

            switch(encryptionChoice){
                case 1:
                    Md5Menu.MD5();
                    break;
                case 2:
                    Sha256Menu.Sha256();
                    break;
                case 3:
                    HmacMenu.HMAC();
                    break;
                case 4:
                    RetrieveMenu.retrieve();
                    break;
                case 0:
                    System.out.println("Back...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            break;
        }
    }

    /**
     * Display single encryption methods options
     */
    private static void displayHashOptions() {
        Common.printTitle("HASH MENU", "Welcome to the HASH Menu!");
        System.out.println("1. Hash with MD5");
        System.out.println("2. Hash with SHA256");
        System.out.println("3. Hash with HMAC");
        System.out.println("4. Retrieve Hashed Password");
        System.out.println("0. Return to main menu");
    }
}
