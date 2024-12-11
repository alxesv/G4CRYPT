import MD5.Md5;
import SHA256.Sha256;

public class HashIntegrityChecker {

    /*
     * Just main being main
     */

    public static void main(String[] args) {
        String message = Checker("Sha256", "test", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
        System.out.println(message);
    }

    /*
     * Checks the method, hashes messageString in the selected method (only md5 and SHA256 are supported rn) 
     * and compares it with the given existingMessageString
     */

    public static String Checker(String method, String messageString, String existingMessageString) {
        if(method.equals("Md5")) {
            String messageStringHashed = Md5.hashString(messageString);
            if(existingMessageString.equals(messageStringHashed)) {
                return "ok";
            } else {
                return "ko";
            }
        } else if(method.equals("Sha256")) {
            String messageStringHashed = Sha256.hashString(messageString);
            if(messageStringHashed.equals(existingMessageString)) {
                return "ok";
            } else {
                return "ko";
            }

        } else {
            System.out.println("Method Error");
            return "Method Error";
        }
        
    }
}