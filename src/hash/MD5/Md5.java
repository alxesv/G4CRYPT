package MD5;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String OUTPUT_FORMAT = "%-20s:%s";

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
        System.out.println(result);
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
     * main caller, currently just outputs stuff in the console
     */

    public static String formatter(String message) {

        //message props

        message = message + 

        return message;
        return 

        // hash props

        byte[] md5InBytes = Md5.digest(message.getBytes(UTF_8));
        System.out.println(String.format(OUTPUT_FORMAT, "MD5 (hex) ", bytesToHex(md5InBytes)));
        // fixed length, 16 bytes, 128 bits.
        System.out.println(String.format(OUTPUT_FORMAT, "MD5 (length)", md5InBytes.length));

    }

    /*
     * just main being main... (adapt if needed)
     */

    public static void main(String[] args) {
        System.out.println("--------------------");
        formatter("testing");
    }
}