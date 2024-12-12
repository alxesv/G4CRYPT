package hash;

import java.util.Optional;

import utils.Lfsr;

public class Hmac {
    
    private static final String PRIVATE_KEY_HMAC_STEP2 = "f1ce739ce739c366f2f03dfa6b318c6f";

    /**
     * Hash input 2 times with salt and pepper
     * @param input the password to hash
     * @return double hashed password
     */

    public static String encrypt(String input, String seed) {

        // get pseudo random value from LFSR with a seed

        String randFromSeed = Lfsr.run(seed, 50, Optional.of(256)).toString(16);

        // hash a first time with randFromCurrentTime as salt

        String firstHashString = Sha256.hashString(input.concat(randFromSeed));

        // hash a second time with PRIVATE_KEY_HMAC_STEP2 as pepper or private key

        String secondHashString = Sha256.hashString(firstHashString.concat(PRIVATE_KEY_HMAC_STEP2));
        
        return secondHashString;
    }
    
}
