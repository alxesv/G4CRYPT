package encryption;

import java.math.BigInteger;
import java.util.Optional;

import utils.Lfsr;

public class Rc4 {

    /**
     * Main for RC4 encryption,
     * @param message the message to encrypt
     * @param seed the seed needed to encrypt or decrypt message seed size adapts to the needed size
     * @return hex format crypted message
     */

    public static String Rc4Encrypt(String message, String seed) {

        // seed may become a parameter

        int[] mainTable = new int[256];
        int[] key = new int[256];

        BigInteger keyBigInteger = Lfsr.run(seed, 50, Optional.of(256));

        String keyString = keyBigInteger.toString(2);

        // fills key array with each lfsr bit

        for(int e = 0; e<256; e++) {
            key[e] = Character.getNumericValue(keyString.charAt(e));
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
        String text = message;
        byte[] cryptedText = rc4EncryptDecrypt(text.getBytes(), mainTable.clone());
        return utils.Common.bytesToHex(cryptedText);
    }


    /**
     * Main for RC4 decryption,
     * @param message the crypted message in hex
     * @param seed the seed needed to encrypt or decrypt message seed size adapts to the needed size
     * @return clear message, decrypted
     */

     public static String Rc4Decrypt(String message, String seed) {

        // seed may become a parameter

        int[] mainTable = new int[256];
        int[] key = new int[256];

        BigInteger keyBigInteger = Lfsr.run(seed, 50, Optional.of(256));

        String keyString = keyBigInteger.toString(2);

        // fills key array with each lfsr bit

        for(int e = 0; e<256; e++) {
            key[e] = Character.getNumericValue(keyString.charAt(e));
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

        byte[] messageToBytes = utils.Common.hexToBytes(message);

        byte[] decryptedText = rc4EncryptDecrypt(messageToBytes, mainTable.clone());
        return new String(decryptedText);
    }

    /**
     * Encrypts or decrypts from input using the maintable and the rc4 algorithm
     * @param input message to encrypt or decrypt
     * @param mainTable seed generated table
     * @return encrypted or decrypted message
     */

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

    /**
     * swapps 2 values without using a temporary variable
     * @param array
     * @param i
     * @param j
     */

    private static void swap(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }
}