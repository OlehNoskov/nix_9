package ua.com.alevel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

public class CustomCSVWrite {

    public CustomCSVWrite(String nameFile) {

        Path pathFile = Paths.get(nameFile);
        try {
            boolean existsFile = Files.exists(pathFile);
            if (!existsFile) {
                Files.createFile(pathFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToCSVFile(List<String[]> entity, String fileName, boolean status) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, status))) {
            for (int i = 0; i < entity.size(); i++) {
                for (int b = 0; b < entity.get(i).length; b++) {
                    bufferedWriter.write(entity.get(i)[b]);
                    if (b != entity.get(i).length - 1) {
                        bufferedWriter.write(",");
                    }
                    if (b == entity.get(i).length - 1) {
                        bufferedWriter.write("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}