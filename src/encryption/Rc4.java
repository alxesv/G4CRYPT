import java.util.Arrays;

public class Rc4 {
    public static void main(String[] args) {
        int[] mainTable = new int[256];
        int[] key = {1, 2, 3, 4, 5}; // Example key, make one from lfsr

        // KSA
        
        for (int i = 0; i < 256; i++) {
            mainTable[i] = i;
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + mainTable[i] + key[i % key.length]) % 256;
            swap(mainTable, i, j);
        }

        // Encrypt & Decrypt
        String plaintext = "Hello, World!"; // Replace with input from menu
        byte[] ciphertext = rc4EncryptDecrypt(plaintext.getBytes(), mainTable.clone());

        System.out.println("Ciphertext: " + Arrays.toString(ciphertext));

        byte[] decryptedText = rc4EncryptDecrypt(ciphertext, mainTable.clone());
        System.out.println("Decrypted: " + new String(decryptedText));
    }

    /*
     * Encrypts from input using the maintable and the rc4 algorithm
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

        return output;
    }

    /*
     * swapper
     */

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}