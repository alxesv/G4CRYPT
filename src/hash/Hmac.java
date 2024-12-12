package hash;

import java.util.Optional;

import utils.Lfsr;
import hash.Sha256;

public class Hmac {
    
    private static final String PRIVATE_KEY_HMAC_STEP2 = "f1ce739ce739c366f2f03dfa6b318c6f";

    public static void main(String[] args) {
        System.out.println(encrypt("null"));
    }

    /**
     * Hash input 2 times with salt and pepper
     * @param input the password to hash
     * @return double hashed password
     */

    public static String encrypt(String input) {

        // get pseudo random value from LFSR with currentTimeMillis() as a seed

        String randFromCurrentTime = Lfsr.run(String.valueOf(System.currentTimeMillis()), 50, Optional.of(128)).toString(16);

        System.out.print("RandFromCurrentTime: ");
        System.out.println(randFromCurrentTime);

        // hash a first time with randFromCurrentTime as salt

        String firstHashString = Sha256.hashString(input.concat(randFromCurrentTime));

        System.out.print("firstHashString: ");
        System.out.println(firstHashString);

        // hash a second time with a private key stored somewhere (or pepper)

        String secondHashString = Sha256.hashString(firstHashString.concat(PRIVATE_KEY_HMAC_STEP2));

        System.out.print("secondHashString: ");
        System.out.println(secondHashString);
        System.out.print("secondHashString size: ");
        System.out.println(secondHashString.toString().length());
        
        return secondHashString;
    }
    
}
