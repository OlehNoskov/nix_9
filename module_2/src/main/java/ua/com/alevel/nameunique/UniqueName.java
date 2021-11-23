package ua.com.alevel.nameunique;

import ua.com.alevel.consolemenu.MenuProgram;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;

public class UniqueName {

    private static final String inputListNames = "module_2/inputListNames.txt";
    private static final String outputFirstUniqueName = "module_2/firstUniqueNameOutput.txt";

    private static TreeSet<String> names = new TreeSet<>();

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
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFirstUniqueName(String fileName) {
        System.out.println();
        System.out.println("=== Певрое уникальное имя ===");

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            if (Files.exists(Paths.get(fileName))) {
                fileWriter.write(names.first());
                System.out.println(names.first()+"\n");
            } else {
                Path newFile = Files.createFile(Paths.get(fileName));
                fileWriter.write(names.first());
                System.out.println(names.first()+"\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}