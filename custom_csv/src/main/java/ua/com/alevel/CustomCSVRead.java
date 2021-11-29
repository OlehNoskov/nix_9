package ua.com.alevel;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class CustomCSVRead {

    public static List<String[]> readCSVFile(String fileName) {

        List<String[]> listEntity = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                String[] line = bufferedReader.readLine().split(",");
                listEntity.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла!");
        }
        return listEntity;
    }

    public static String[] search(List<String[]> listEntity, String ids) {
        String[] id = new String[2];
        for (int i = 0; i < listEntity.size(); i++) {
            for (int b = 0; b < listEntity.size(); b++) {
                if (listEntity.get(i)[b].equals(ids)) {
                    id = listEntity.get(i);
                    return id;
                }
            }
        }
        return id;
    }
}