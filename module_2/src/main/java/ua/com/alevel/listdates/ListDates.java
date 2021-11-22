package ua.com.alevel.listdates;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListDates {

    public static List<String> inputListDates = new ArrayList<>();
    public static List<String> outputListDates = new ArrayList<>();

    private static final String inputDates = "module_2/inputDates.txt";
    private static final String outputDates = "module_2/outputCorrectDates.txt";

    public static void showListInputAndOutputDates() {
        try {
            readingInputDates(inputDates);
            addingCorrectDates(outputDates);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

    private static void readingInputDates(String fileName) throws FileNotFoundException {
        System.out.println("=== Список прочитанных дат из файла === ");
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                inputListDates.add(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addingCorrectDates(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String regex1 = "\\d{4}.\\d{2}.\\d{2}";
        String regex2 = "\\d{2}.\\d{2}.\\d{4}";
        String regex3 = "\\d{2}-\\d{2}-\\d{4}";

        if (inputListDates.size() != 0) {
            for (String date : inputListDates) {
                if (date.matches(regex1)) {
                    String[] arrayDate = date.trim().split("/");
                    for (int i = 0; i < arrayDate.length; i++) {
                        stringBuilder.append(arrayDate[i]);
                    }
                    stringBuilder.append("\n");
                }
                else if (date.matches(regex3)) {
                    String[] arrayDate2 = date.trim().split("-");
                    stringBuilder.append(arrayDate2[2]);
                    stringBuilder.append(arrayDate2[0]);
                    stringBuilder.append(arrayDate2[1]);
                    stringBuilder.append("\n");
                }
                else if (date.matches(regex2)) {
                    String[] arrayDate1 = date.trim().split("/");
                    for (int i = arrayDate1.length - 1; i >= 0; i--) {
                        stringBuilder.append(arrayDate1[i]);
                    }
                    stringBuilder.append("\n");
                }
            }
            outputListDates.add(stringBuilder.toString());
        } else
            System.out.println("Список дат пуст!");
        writeCorrectDates();
    }

    private static void writeCorrectDates() {
        System.out.println();
        System.out.println("=== Список записанных дат в файл ===");

        try (FileWriter fileWriter = new FileWriter(outputDates)) {
            if (Files.exists(Paths.get(outputDates))) {
                for (String date : outputListDates) {
                    fileWriter.write(date + System.lineSeparator());
                    System.out.println(date);
                }
            } else {
                Path newFile = Files.createFile(Paths.get(outputDates));
                for (String date : outputListDates) {
                    fileWriter.write(date + System.lineSeparator());
                    System.out.println(date);
                }
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}