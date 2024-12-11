package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData {

    /**
     * Save the data to a file with no key
     * @param name name of the service related to the password
     * @param password encrypt password to save
     * @param method encryption method used
     */
    public static void saveData(String name, String password, String method) {
        saveData(name, password, method, "null");
    }
    /**
     * Save the data to a file with a key
     * @param key key used for encryption
     */
    public static void saveData(String name, String password, String method, String key) {
        File file = new File("data.csv");
        boolean fileExists = file.exists();

        // Store the data in the file
        try (FileWriter writer = new FileWriter(file, true)) {
            // If the file does not exist, write the header
            if (!fileExists) {
                writer.write("Password Store\n");
            }
            // Write the data to the file
            writer.write(name + ":" + password + ":" + method + ":" + key + "\n");
            System.out.println("Data saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}