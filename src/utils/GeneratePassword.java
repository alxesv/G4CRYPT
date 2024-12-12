package utils;

import java.math.BigInteger;

public class GeneratePassword {
    /**
     * Generate a random password
     * @return the generated password
     */
    public static String generate(String seed) {

        // Generate a random number between 0 and 1
        double passwordLengthDouble = generateDouble(seed, 10);

        // Define the length of the password from 12 to 64
        int length = (int) (passwordLengthDouble * 53) + 12;

        // Define the characters to use in the password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder password = new StringBuilder();

        // Generate the password
        for (int i = 0; i < length; i++) {
            // Use the iteration of the loop to generate a random number
            int index = (int) (characters.length() * generateDouble(seed, i+5));
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    /**
     * Generate a double between 0 and 1
     * @param seed the seed
     * @param iter the iteration
     * @return the generated double
     */
    private static double generateDouble(String seed, int iter){
        String randomNumberString1 = Lfsr.run(seed, iter, java.util.Optional.empty()).toString();
        String doubleString1 = "0." + randomNumberString1.substring(randomNumberString1.length() - 2);
        return Double.parseDouble(doubleString1);
    }
}
