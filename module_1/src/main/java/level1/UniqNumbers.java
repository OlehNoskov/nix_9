package level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UniqNumbers {

    private static Set<Integer> uniqNumbers = new HashSet<>();

    public static void countDigit(BufferedReader reader) throws IOException {
        System.out.println("===== Поиск количества уникальных символов =====");
        System.out.println("Введите цифры:");

        String line = reader.readLine();
        String[] numbers = line.trim().split(" ");

        for (String number : numbers) {
            char[] digit = number.toCharArray();

            for (int charNumber : digit) {
                if (Character.isDigit(charNumber))
                    uniqNumbers.add(charNumber);
            }
        }
        System.out.println("Число уникальных символов (чисел): " + uniqNumbers.size()+"\n");
        cleanMap();
        ShowFirstLevel.showTasksFirstLevel(reader);
    }

    private static void cleanMap() {
        uniqNumbers.clear();
    }
}