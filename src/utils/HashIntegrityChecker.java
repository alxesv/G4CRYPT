package utils;

import java.nio.file.OpenOption;
import java.util.Optional;

import hash.Hmac;
import hash.Md5;
import hash.Sha256;

public class HashIntegrityChecker {

    /**
     * checks hash integrity
     * @param method the method used to hash
     * @param messageString the text matching the hash
     * @param the hash previously obtained 
     */

    public static boolean Checker(String method, String messageString, String existingMessageString, Optional<String> seed) {
        if(method.equals("MD5")) {
            String messageStringHashed = Md5.hashString(messageString);
            return existingMessageString.equals(messageStringHashed);
        } else if(method.equals("SHA256")) {

            String messageStringHashed = Sha256.hashString(messageString);
            return messageStringHashed.equals(existingMessageString);

        } else if(method.equals("HMAC") && seed.isPresent()){

            // transforming Optionnal<String> seed to String seed
            String seedValue = seed.get();
            String messageStringHashed = Hmac.hashString(messageString, seedValue);
            return messageStringHashed.equals(existingMessageString);

        } else {
            System.out.println("Method Error or seed missing for HMAC");
            return false;
        }
    }
}