package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstTask {
    public void sumNumbers(BufferedReader reader) throws IOException {
        int sumResult = 0;
        System.out.println("Запуск 1-го домашнего задания");
        System.out.println("Введите в строке цифры для нахождения их суммы:");

        String line = reader.readLine();

        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (Character.isDigit(symbol)) {
                int number = Integer.parseInt(String.valueOf(symbol));
                sumResult += number;
            }
        }
        System.out.println("Сумма всех цифр в строке: " + sumResult);
    }
}
