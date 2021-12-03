package ua.com.alevel.listdates;

import ua.com.alevel.consolemenu.MenuProgram;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListDates {

    private static final List<String> inputListDates = new ArrayList<>();
    private static final List<String> outputListDates = new ArrayList<>();

    private static final String inputDates = "inputDates.txt";
    private static final String outputDates = "outputCorrectDates.txt";

    public static void showListInputAndOutputDates() {
        try {
            readingInputDates(inputDates);
            addingCorrectDates();
            writeCorrectDates(outputDates);
            MenuProgram.run();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

    private static void readingInputDates(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                inputListDates.add(line);
            }
            System.out.println("Данные из файла " + inputDates + " успешно прочитаты!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addingCorrectDates() {
        StringBuilder stringBuilder = new StringBuilder();
        String regex1 = "\\d{4}.\\d{2}.\\d{2}";
        String regex2 = "\\d{2}.\\d{2}.\\d{4}";
        String regex3 = "\\d{2}-\\d{2}-\\d{4}";

        if (inputListDates.size() != 0) {
            for (String date : inputListDates) {
                if (date.matches(regex1)) {
                    String[] arrayDate = date.trim().split("/");
                    if (DateValid.calendarIsValid(Integer.parseInt(arrayDate[0]),
                            Integer.parseInt(arrayDate[1]),
                            Integer.parseInt(arrayDate[2]))) {
                        for (int i = 0; i < arrayDate.length; i++) {
                            stringBuilder.append(arrayDate[i]);
                        }
                        stringBuilder.append("\n");
                    }
                } else if (date.matches(regex3)) {
                    String[] arrayDate2 = date.trim().split("-");
                    String year = arrayDate2[2];
                    String month = arrayDate2[0];
                    String day = arrayDate2[1];
                    if (DateValid.calendarIsValid(Integer.parseInt(year),
                            Integer.parseInt(month),
                            Integer.parseInt(day))) {
                        stringBuilder.append(year);
                        stringBuilder.append(month);
                        stringBuilder.append(day);
                        stringBuilder.append("\n");
                    }
                } else if (date.matches(regex2)) {
                    String[] arrayDate1 = date.trim().split("/");
                    String year = arrayDate1[2];
                    String month = arrayDate1[1];
                    String day = arrayDate1[0];
                    if (DateValid.calendarIsValid(Integer.parseInt(year),
                            Integer.parseInt(month),
                            Integer.parseInt(day))) {
                        for (int i = arrayDate1.length - 1; i >= 0; i--) {
                            stringBuilder.append(arrayDate1[i]);
                        }
                        stringBuilder.append("\n");
                    }
                }
            }
            outputListDates.add(stringBuilder.toString());
        } else
            System.out.println("Список дат пуст!");
    }

    private static void writeCorrectDates(String fileName) {
        System.out.println();
        System.out.println("Успешно записанные данные:");
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            if (Files.exists(Paths.get(fileName))) {
                for (String date : outputListDates) {
                    fileWriter.write(date + System.lineSeparator());
                    System.out.println(date);
                }
            } else {
                Path newFile = Files.createFile(Paths.get(fileName));
                for (String date : outputListDates) {
                    fileWriter.write(date + System.lineSeparator());
                    System.out.println(date);
                }
            }
            fileWriter.flush();
            inputListDates.clear();
            outputListDates.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}