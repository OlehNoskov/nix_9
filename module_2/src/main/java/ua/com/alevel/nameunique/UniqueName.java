package ua.com.alevel.nameunique;

import ua.com.alevel.consolemenu.MenuProgram;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueName {

    private static final String inputListNames = "inputListNames.txt";
    private static final String outputFirstUniqueName = "firstUniqueNameOutput.txt";

    private static List<String> listNames = new ArrayList<>();
    private static List<String> uniqueNames = new ArrayList<>();

    public static void showFirstUniqueName() {
        try {
            readingInputNames(inputListNames);
            writeFirstUniqueName(outputFirstUniqueName);
            MenuProgram.run();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

    private static void readingInputNames(String fileName) throws FileNotFoundException {
        System.out.println("=== Список имен === ");
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                listNames.add(line);
            }
            uniqueNames = listNames.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFirstUniqueName(String fileName) {
        System.out.println();
        System.out.println("=== Первое уникальное имя ===");

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            if (Files.exists(Paths.get(fileName))) {
                fileWriter.write(uniqueNames.get(0));
                System.out.println(uniqueNames.get(0) + "\n");
            } else {
                Path newFile = Files.createFile(Paths.get(fileName));
                fileWriter.write(uniqueNames.get(0));
                System.out.println(uniqueNames.get(0) + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}