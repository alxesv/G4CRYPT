package encryption;

import java.util.ArrayList;
import java.util.List;

//import static utils.SaveData.saveData;

public class Rot {

    /** Encrypt a text with a rotation
     * @param text the text to encrypt
     * @param rot the rotation to apply
     * @return the encrypted text
     */

    private static List<String> passwordData = new ArrayList<>();

    public static String encryptRot(String text, int rot) {

            StringBuilder result = new StringBuilder();
            // loop on every letter of the word to encrypt
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // check if the letter is a letter
                //if (Character.isLetter(c)) {
                    if (Character.isLowerCase(c)) {
                        result.append((char) (96 + (c - 96 + rot) % 26));
                        System.out.println(result);
                    } else {
                        result.append((char) (40 + (c - 40 + rot) % 26));
                    }
                //} else {
                    // if it is not a letter, display the word with an error message
                    //System.out.printf("Seule les lettres sont acceptées");
                    //return text;
                //}
            }
            // store the encrypted word and the rotation to the list
            passwordData.add(result.toString());
            passwordData.add(String.valueOf(rot));
            passwordData.add("ROT");
            return passwordData.get(0);
    }

    //public static void storeData() {
    //    System.out.println(passwordData);
    //    saveData("amazon", passwordData.get(0), passwordData.get(2));
    //}

        public static String decryptRot (String text, int rot){
            // decrypt with the encrypt function and a negative rotation
            return encryptRot(text, 26 - rot);
        }

}
