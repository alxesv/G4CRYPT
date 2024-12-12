package hash;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import utils.Common;

public class Md5 {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * tries the selected method and digests with the selected method
     * @input the text to hash
     */

    private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
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