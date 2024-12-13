package hash;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import utils.Common;

public class Md5 {
    
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * Generates an MD5 hash for the given input byte array.
     *
     * @param input The input byte array to hash.
     * @return A byte array representing the MD5 hash of the input.
     * @throws IllegalArgumentException If the MD5 algorithm is not available.
     */
    private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            // Attempt to initialize the MessageDigest with the MD5 algorithm
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // Throw an exception if the MD5 algorithm is not supported
            throw new IllegalArgumentException(e);
        }
        // Compute and return the MD5 hash of the input byte array
        return md.digest(input);
    }

    /**
     * main caller
     * @param message message to hash
     */
    public static String hashString(String message) {

        // calls hashing function
        byte[] md5InBytes = Md5.digest(message.getBytes(UTF_8));

        return Common.bytesToHex(md5InBytes);
    }
}
