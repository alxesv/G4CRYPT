package MD5;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /*
     * tries the selected method and digests with the selected method
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

    /*
     * transforms bytes to hex, uses stringbuilder
     */

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    /*
     * main caller
     */

    public static String formatter(String message) {

        // Hashing function

        byte[] md5InBytes = Md5.digest(message.getBytes(UTF_8));

        return bytesToHex(md5InBytes);
    }

    /*
     * just main being main... (adapt if needed)
     */

    public static void main(String[] args) {
        System.out.println("--------------------");
        formatter("testing");
    }
}