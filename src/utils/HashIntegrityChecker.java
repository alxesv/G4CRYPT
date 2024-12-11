package utils;

import hash.Md5;
import hash.Sha256;

public class HashIntegrityChecker {

    /**
     * checks hash integrity
     * @param method the method used to hash
     * @param messageString the text matching the hash
     * @param the hash previously obtained 
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