package utils;

import encryption.Aes;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveCSV {

    public static List<String> getListCSV() throws Exception {
        String pathToFile = "data.csv";
        List<String> dataList = new ArrayList<>();
        SecretKey aesKey = AesKeyManager.loadOrGenerateKey();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;

            // Read each line from the file, decrypt it, and add it to the dataList
            while ((line = reader.readLine()) != null) {
                String decryptedLine = Aes.decrypt(line, aesKey);
                if (decryptedLine != null) {
                    dataList.add(decryptedLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
