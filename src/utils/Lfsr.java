package utils;

import java.math.BigInteger;
import java.util.Optional;

public class Lfsr {
    /**
     * Run the LFSR algorithm
     * @param seed the seed to start the LFSR
     * @param iterations the number of iterations to run
     */
public static BigInteger run(String seed, int iterations, Optional<Integer> bitLength) {
        // Default length of the LFSR
        int defaultLength = 16;
        // Initialize length of the LFSR
        int length;
        // If length is not provided, use the default length
        length = bitLength.orElse(defaultLength);

        // Initialize the random number
        BigInteger randomNumber = BigInteger.ZERO;
        // Turn seed into binary string
        StringBuilder rawSeedBinary = new StringBuilder();
        for (int i = 0; i < seed.length(); i++) {
            int c = seed.charAt(i);
            String binary = Integer.toBinaryString(c);
            rawSeedBinary.append(binary);
        }

        // Repeat the seed if it is less than length
        if(rawSeedBinary.length() < length){
            int diff = length - rawSeedBinary.length();
            for(int i = 0; i < diff; i++){
                rawSeedBinary.insert(0, rawSeedBinary.charAt(i % rawSeedBinary.length()));
            }
        }

        // Truncate to length if it is greater than length
        StringBuilder seedBinary = new StringBuilder(rawSeedBinary.substring(0, length));


        // Run the LFSR @iterations times
        for(int i = 0; i < iterations; i++){

            // Append the XOR of the last 2 bits (and delete the first bit)
            seedBinary.append(seedBinary.charAt(seedBinary.length() - 1) ^ seedBinary.charAt(seedBinary.length() - 2));
            seedBinary.deleteCharAt(0);

            // Append the XOR of the 3rd and 8th bits
            seedBinary.append(seedBinary.charAt(2) ^ seedBinary.charAt(7));
            seedBinary.deleteCharAt(0);

            // Append the XOR of the first and last bits
            seedBinary.append(seedBinary.charAt(0) ^ seedBinary.charAt(seedBinary.length() - 1));
            seedBinary.deleteCharAt(0);

            // Append the AND of the 2nd and 5th bits
            if(seedBinary.charAt(1) == '1' && seedBinary.charAt(4) == '1'){
                seedBinary.append('1');
            } else {
                seedBinary.append('0');
            }
            seedBinary.deleteCharAt(0);

            // Append the OR of the 4th and 6th bits
            if(seedBinary.charAt(3) == '1' || seedBinary.charAt(5) == '1'){
                seedBinary.append('1');
            } else {
                seedBinary.append('0');
            }
            // Delete the 6th bit
            seedBinary.deleteCharAt(5);

            // Convert to BigInteger on the last iteration
            if(i == iterations - 1){
                randomNumber = new BigInteger(seedBinary.toString(), 2);
            }
        }
        return randomNumber;
    }
}
