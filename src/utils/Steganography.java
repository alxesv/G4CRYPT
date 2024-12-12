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

        // Prepare the message by adding a signature and a null terminator
        byte[] messageBytes = ("MSG" + message + '\0').getBytes(); // Add 'MSG' at the start and null byte to mark the end of the message
        int messageLength = messageBytes.length;
        int width = image.getWidth();
        int height = image.getHeight();

        // Check if the message fits in the image's available capacity
        if (messageLength * 8 > width * height * 3) {
            throw new IllegalArgumentException("Message is too large to fit in the image.");
        }

        int messageIndex = 0; // Current index in the message
        int bitIndex = 0;     // Current bit being processed in the current byte

        // Iterate over each pixel in the image
        outerLoop:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                // Extract the red, green, and blue components of the pixel
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Modify the least significant bits of R, G, and B with the message bits
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

                // Reassemble the pixel with the modified RGB values
                int newRgb = (r << 16) | (g << 8) | b;
                image.setRGB(x, y, newRgb);

                // Exit the loop once the entire message has been encoded
                if (messageIndex >= messageLength) {
                    break outerLoop;
                }
            }
        }

        // Save the modified image to the specified output file
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
        BufferedImage image = ImageIO.read(new File(imagePath)); // Load the image
        int width = image.getWidth();  // Get the image width
        int height = image.getHeight(); // Get the image height

        StringBuilder messageBuilder = new StringBuilder(); // Stores the retrieved message
        byte currentByte = 0; // Current byte being constructed
        int bitIndex = 0;     // Current bit position within the byte
        boolean hasSignature = false; // Indicates if the 'MSG' signature has been detected

        // Iterate over each pixel in the image
        outerLoop:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                // Extract the red, green, and blue components of the pixel
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Read bits from R, G, and B channels
                currentByte = (byte) ((currentByte << 1) | getLeastSignificantBit(r));
                bitIndex++;
                if (bitIndex == 8) {
                    if (!hasSignature && messageBuilder.length() < 3) {
                        messageBuilder.append((char) currentByte);
                        if (messageBuilder.toString().equals("MSG")) {
                            hasSignature = true;
                            messageBuilder.setLength(0); // Clear signature from result
                        } else if (messageBuilder.length() == 3) {
                            break outerLoop; // No valid signature
                        }
                    } else if (hasSignature) {
                        if (currentByte == 0) break outerLoop; // Null byte indicates end of message
                        messageBuilder.append((char) currentByte);
                    }
                    currentByte = 0;
                    bitIndex = 0;
                }

                // Repeat the same for green and blue channels
                currentByte = (byte) ((currentByte << 1) | getLeastSignificantBit(g));
                bitIndex++;
                if (bitIndex == 8) {
                    if (!hasSignature && messageBuilder.length() < 3) {
                        messageBuilder.append((char) currentByte);
                        if (messageBuilder.toString().equals("MSG")) {
                            hasSignature = true;
                            messageBuilder.setLength(0); // Clear signature from result
                        } else if (messageBuilder.length() == 3) {
                            break outerLoop; // No valid signature
                        }
                    } else if (hasSignature) {
                        if (currentByte == 0) break outerLoop;
                        messageBuilder.append((char) currentByte);
                    }
                    currentByte = 0;
                    bitIndex = 0;
                }

                currentByte = (byte) ((currentByte << 1) | getLeastSignificantBit(b));
                bitIndex++;
                if (bitIndex == 8) {
                    if (!hasSignature && messageBuilder.length() < 3) {
                        messageBuilder.append((char) currentByte);
                        if (messageBuilder.toString().equals("MSG")) {
                            hasSignature = true;
                            messageBuilder.setLength(0);
                        } else if (messageBuilder.length() == 3) {
                            break outerLoop;
                        }
                    } else if (hasSignature) {
                        if (currentByte == 0) break outerLoop;
                        messageBuilder.append((char) currentByte);
                    }
                    currentByte = 0;
                    bitIndex = 0;
                }
            }
        }

        if (!hasSignature) {
            return "No hidden message found."; // Return if no valid signature was detected
        }

        return messageBuilder.toString(); // Return the extracted message
    }

    /**
     * Sets the least significant bit of a byte to the specified value.
     *
     * @param value The original byte value.
     * @param bit   The bit to set (0 or 1).
     * @return The modified byte with the least significant bit set to the specified value.
     */
    private static int setLeastSignificantBit(int value, int bit) {
        return (value & 0xFE) | (bit & 1); // Clear the LSB and set it to the specified value
    }

    /**
     * Retrieves the least significant bit of a byte.
     *
     * @param value The byte value.
     * @return The least significant bit (0 or 1).
     */
    private static int getLeastSignificantBit(int value) {
        return value & 1; // Extract the LSB
    }

    /**
     * Retrieves the specified bit from a byte.
     *
     * @param value The byte value.
     * @param bit   The bit position to retrieve (0-7).
     * @return The value of the specified bit (0 or 1).
     */
    private static int getBit(byte value, int bit) {
        return (value >> (7 - bit)) & 1; // Shift and mask to extract the specified bit
    }
}
