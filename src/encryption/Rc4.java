package encryption;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;

import utils.Lfsr;

public class Rc4 {

    /*
     * Main for RC4 encryption,
     * @param message the message to encrypt
     */

    public static void Rc4Cipher(String message) {

        // seed may become a parameter

        String seed = "thisisaseed";

        int[] mainTable = new int[256];
        int[] key = new int[256];

        BigInteger tempt = Lfsr.run(seed, 50, Optional.of(256));
        System.out.println(tempt);

        String test = tempt.toString(2);
        System.out.println(test);
        System.out.println(test.charAt(10));

        // fills key array with each lfsr bit

        for(int e = 0; e<256; e++) {
            key[e] = Character.getNumericValue(test.charAt(e));
            System.out.print(key[e]);
        }

        // fills mainTable from index 0 with value 0 to index 255 vith value 255
        
        for (int i = 0; i < 256; i++) {
            mainTable[i] = i;
        }

        // KSA

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + mainTable[i] + key[i]) % 256;
            swap(mainTable, i, j);
        }

        // Encrypt & Decrypt
        String text = message; // Replace with input from menu
        byte[] cryptedText = rc4EncryptDecrypt(text.getBytes(), mainTable.clone());
        System.out.println("\nCiphertext: " + Arrays.toString(cryptedText));

        byte[] decryptedText = rc4EncryptDecrypt(cryptedText, mainTable.clone());
        System.out.println("Decrypted: " + new String(decryptedText));
    }

    // Encrypts from input using the maintable and the rc4 algorithm

    public static byte[] rc4EncryptDecrypt(byte[] input, int[] mainTable) {
        // PRGA
        int i = 0, j = 0;
        byte[] output = new byte[input.length];

        for (int k = 0; k < input.length; k++) {
            i = (i + 1) % 256;
            j = (j + mainTable[i]) % 256;
            swap(mainTable, i, j);

            int rnd = mainTable[(mainTable[i] + mainTable[j]) % 256];
            output[k] = (byte) (input[k] ^ rnd);
        }

        // @return the encrypted or decrypted message

        return output;
    }

    // swapp 2 values without using a temporary variable

    private static void swap(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }
}