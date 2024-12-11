package utils;

import java.math.BigInteger;

public class Lfsr {
    /**
     * Run the LFSR algorithm
     * @param seed the seed to start the LFSR
     * @param iterations the number of iterations to run
     */
    public static BigInteger run(String seed, int iterations) {
        BigInteger randomNumber = BigInteger.ZERO;
        // Turn seed into binary string
        StringBuilder rawSeedBinary = new StringBuilder();
        for (int i = 0; i < seed.length(); i++) {
            int c = seed.charAt(i);
            String binary = Integer.toBinaryString(c);
            rawSeedBinary.append(binary);
        }

        // Repeat the seed if it is less than 16 bits
        if(rawSeedBinary.length() < 16){
            System.out.println(rawSeedBinary);
            int diff = 16 - rawSeedBinary.length();
            for(int i = 0; i < diff; i++){
                rawSeedBinary.insert(rawSeedBinary.length(), rawSeedBinary.charAt(i));
            }
            System.out.println(rawSeedBinary);
        }

        // Truncate to 16 bits to limit the size
        StringBuilder seedBinary = new StringBuilder(rawSeedBinary.substring(0, 16));


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

            // Convert to int and print
            BigInteger result = new BigInteger(seedBinary.toString(), 2);
            randomNumber = result;
            System.out.println(result);
        }
        return randomNumber;
    }
}
