package hash;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
     * transforms bytes to hex, uses stringbuilder
     */

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * main caller
     * @param message the message to hash
     */

    public static String hashString(String message) {

        // Hashing function

        byte[] sha256InBytes = Sha256.digest(message.getBytes(UTF_8));

        return bytesToHex(sha256InBytes);
    }
}