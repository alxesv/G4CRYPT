package encryption;

import java.util.ArrayList;
import java.util.List;

import static utils.SaveData.saveData;

//import static utils.SaveData.saveData;

public class Rot {

    /** Encrypt a text with a rotation
     * @param text the text to encrypt
     * @param rot the rotation to apply
     * @return the encrypted text
     */

    public static String encryptRot(String text, int rot) {
            StringBuilder result = new StringBuilder();
            // loop on every letter of the word to encrypt
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // check if the letter is a letter
                if (Character.isLetter(c)) {
                    if (Character.isLowerCase(c)) {
                        result.append((char) (96 + (c - 96 + rot) % 26));
                        System.out.println(result);
                    } else {
                        result.append((char) (40 + (c - 40 + rot) % 26));
                    }
                } else {
                    // if it is not a letter, display the word with an error message
                    System.out.printf("Only letters are allowed in the text. %s is not a letter. \n", c);
                    return text;
                }
            }
            return result.toString();
    }

    /** Decrypt a text with a rotation
     * @param text the text to decrypt
     * @param text
     * @param rot
     * @return
     */
        public static String decryptRot (String text, int rot){
            // decrypt with the encrypt function and a negative rotation
            return encryptRot(text, 26 - rot);
        }

}
