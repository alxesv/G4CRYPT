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
            System.out.print("\u001B[36mEnter your choice: \u001B[0m");

            while(!scanner.hasNextInt()){
                System.out.println("Please enter a valid choice.");
                displayHashOptions();
                System.out.print("\u001B[36mEnter your choice: \u001B[0m");
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
        System.out.println("\u001B[36m Choose a hashing method: \u001B[0m");
        System.out.println("\u001B[36m1.\u001B[0m Hash with MD5");
        System.out.println("\u001B[36m2.\u001B[0m Hash with SHA256");
        System.out.println("\u001B[36m3.\u001B[0m Hash with HMAC");
        System.out.println("\u001B[1;31m0.\u001B[0m Return to main menu");
    }
}
