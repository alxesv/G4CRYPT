import MD5.Md5;
import SHA256.Sha256;

public class HashIntegrityChecker {
    public static void main(String[] args) {
        
        
    }
    public void Checker(String method, String messageString, String existingMessageString) {
        if(method.equals("Md5")) {
            Md5.formatter(existingMessageString);


        } else if(method.equals("Sha256")) {

        } else {
            System.out.println("Error method");
        }
        
    }
}