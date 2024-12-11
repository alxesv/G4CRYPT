import MD5.Md5;
import SHA256.Sha256;

public class HashIntegrityChecker {
    public static void main(String[] args) {
        
        
    }
    public void Checker(String method, String messageString, String existingMessageString) {
        if(method.equals("Md5")) {
            String messageStringFormatted = Md5.formatter(messageString);
            if(existingMessageString.equals(messageStringFormatted)) {
                System.out.println("ok");
            } else {
                System.out.println("ko");
            }
        } else if(method.equals("Sha256")) {
            String messStringaStringFormatted = Sha256.formatter(messageString);

        } else {
            System.out.println("Method Error");
        }
        
    }
}