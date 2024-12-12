package hash;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import utils.Common;

public class Sha256 {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * tries the selected method and digests with the selected method
     */

    private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    /**
     * main caller
     * @param message the message to hash
     * @return the hashed message
     */

    public static String hashString(String message) {

        // calls hashing function

        byte[] sha256InBytes = Sha256.digest(message.getBytes(UTF_8));

        return Common.bytesToHex(sha256InBytes);
    }
}