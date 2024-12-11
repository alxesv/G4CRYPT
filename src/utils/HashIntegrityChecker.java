package utils;

import hash.Md5;
import hash.Sha256;

public class HashIntegrityChecker {

    /*
     * Just main being main
     */

    public static void main(String[] args) {
        boolean message = Checker("Sha256", "test", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
        System.out.println(message);
    }

    /*
     * Checks the method, hashes messageString in the selected method (only md5 and SHA256 are supported right now) 
     * and compares it with the given existingMessageString
     */

    public static boolean Checker(String method, String messageString, String existingMessageString) {
        if(method.equals("Md5")) {
            String messageStringHashed = Md5.hashString(messageString);
            if(existingMessageString.equals(messageStringHashed)) {
                return true;
            } else {
                return false;
            }
        } else if(method.equals("Sha256")) {
            String messageStringHashed = Sha256.hashString(messageString);
            if(messageStringHashed.equals(existingMessageString)) {
                return true;
            } else {
                return false;
            }

        } else {
            System.out.println("Method Error");
            return false;
        }
    }
}