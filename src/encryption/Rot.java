package encryption;

import java.util.ArrayList;
import java.util.List;

public class Rot {

    private static List<String> passwordData = new ArrayList<>();

    public static String encryptRot(String text, int rot) {
        // check if the word is totally in lower or upper case
        if (text.equals(text.toLowerCase()) || text.equals(text.toUpperCase())) {
            StringBuilder result = new StringBuilder();
            // loop on every letter of the word to encrypt
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // check if the letter is a letter
                if (Character.isLetter(c)) {
                    result.append((char) (c + rot % 26));
                } else {
                    // if it is not a letter, display the word with an error message
                    System.out.printf("Seule les lettres sont acceptées");
                    return text;
                }
            }
            // store the encrypted word and the rotation to the list
            passwordData.add(result.toString());
            passwordData.add(String.valueOf(rot));
            return passwordData.toString();
        } else {
            // error message if the word contains lower and upper case
            System.out.printf("Seule les mots en minuscule ou en majuscule sont acceptés");
            return text;
        }
    }


        public static String decryptRot (){
            // extract the text and the rotation from the list
            String text = passwordData.get(0);
            int rot = Integer.parseInt(passwordData.get(1));
            // decrypt with the encrypt function and a negative rotation
            return encryptRot(text, 26 - rot);
        }

}
