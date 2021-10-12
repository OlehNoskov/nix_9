package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstTask {
    public static void sumNumbers() {
        int sumResult = 0;
        System.out.println("Введите строку:");

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            for (int i = 0; i < line.length(); i++) {
                char symbol = line.charAt(i);
                if (Character.isDigit(symbol)) {
                    int number = Integer.parseInt(String.valueOf(symbol));
                    sumResult += number;
                }
            }
            System.out.println("Сумма всех цифр в строке: " + sumResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
