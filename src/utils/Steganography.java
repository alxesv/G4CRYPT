package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Steganography class provides methods to hide a text message in an image
 * using the Least Significant Bit (LSB) technique.
 */
public class Steganography {
    /**
     * Hides a text message in the provided image file and saves the result.
     *
     * @param inputImagePath  Path to the input image file.
     * @param outputImagePath Path to save the resulting image with the hidden message.
     * @param message         The text message to hide in the image.
     * @throws IOException If an I/O error occurs.
     */
    public static void hideTextInImage(String inputImagePath, String outputImagePath, String message) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputImagePath));

        byte[] messageBytes = (message + '\0').getBytes(); // Add null byte to mark end of message
        int messageLength = messageBytes.length;
        int width = image.getWidth();
        int height = image.getHeight();

        if (messageLength * 8 > width * height * 3) {
            throw new IllegalArgumentException("Message is too large to fit in the image.");
        }

        int messageIndex = 0;
        int bitIndex = 0;

        outerLoop:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                if (messageIndex < messageLength) {
                    r = setLeastSignificantBit(r, getBit(messageBytes[messageIndex], bitIndex++));
                    if (bitIndex == 8) {
                        bitIndex = 0;
                        messageIndex++;
                    }
                }
                if (messageIndex < messageLength) {
                    g = setLeastSignificantBit(g, getBit(messageBytes[messageIndex], bitIndex++));
                    if (bitIndex == 8) {
                        bitIndex = 0;
                        messageIndex++;
                    }
                }
                if (messageIndex < messageLength) {
                    b = setLeastSignificantBit(b, getBit(messageBytes[messageIndex], bitIndex++));
                    if (bitIndex == 8) {
                        bitIndex = 0;
                        messageIndex++;
                    }
                }

                int newRgb = (r << 16) | (g << 8) | b;
                image.setRGB(x, y, newRgb);

                if (messageIndex >= messageLength) {
                    break outerLoop;
                }
            }
        }

        ImageIO.write(image, "png", new File(outputImagePath));
    }

    /**
     * Retrieves a hidden text message from the provided image file.
     *
     * @param imagePath Path to the image file with the hidden message.
     * @return The hidden text message.
     * @throws IOException If an I/O error occurs.
     */
    public static String retrieveTextFromImage(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));

        int width = image.getWidth();
        int height = image.getHeight();

        StringBuilder messageBuilder = new StringBuilder();
        byte currentByte = 0;
        int bitIndex = 0;

        outerLoop:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                currentByte = (byte) ((currentByte << 1) | getLeastSignificantBit(r));
                bitIndex++;
                if (bitIndex == 8) {
                    if (currentByte == 0) break outerLoop; // Null byte indicates end of message
                    messageBuilder.append((char) currentByte);
                    currentByte = 0;
                    bitIndex = 0;
                }

                currentByte = (byte) ((currentByte << 1) | getLeastSignificantBit(g));
                bitIndex++;
                if (bitIndex == 8) {
                    if (currentByte == 0) break outerLoop;
                    messageBuilder.append((char) currentByte);
                    currentByte = 0;
                    bitIndex = 0;
                }

                currentByte = (byte) ((currentByte << 1) | getLeastSignificantBit(b));
                bitIndex++;
                if (bitIndex == 8) {
                    if (currentByte == 0) break outerLoop;
                    messageBuilder.append((char) currentByte);
                    currentByte = 0;
                    bitIndex = 0;
                }
            }
        }

        return messageBuilder.toString();
    }

    /**
     * Sets the least significant bit of a byte to the specified value.
     *
     * @param value The original byte value.
     * @param bit   The bit to set (0 or 1).
     * @return The modified byte with the least significant bit set to the specified value.
     */
    private static int setLeastSignificantBit(int value, int bit) {
        return (value & 0xFE) | (bit & 1);
    }

    /**
     * Retrieves the least significant bit of a byte.
     *
     * @param value The byte value.
     * @return The least significant bit (0 or 1).
     */
    private static int getLeastSignificantBit(int value) {
        return value & 1;
    }

    /**
     * Retrieves the specified bit from a byte.
     *
     * @param value The byte value.
     * @param bit   The bit position to retrieve (0-7).
     * @return The value of the specified bit (0 or 1).
     */
    private static int getBit(byte value, int bit) {
        return (value >> (7 - bit)) & 1;
    }
}
