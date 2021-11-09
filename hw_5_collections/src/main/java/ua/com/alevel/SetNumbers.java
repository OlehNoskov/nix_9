package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

public class SetNumbers {
    public static void createNewMathSet(BufferedReader reader) throws IOException {
        System.out.println("===== Создание масива уникальных чисел =====");
        System.out.print("Введите числа через пробел:");
        String line = reader.readLine();

//        String[] numbers = line.trim().split(" ");
//        Number[] numberSet = new Number[numbers.length];
//
//        for (String number : numbers) {
//            char[] digit = number.toCharArray();
//
//            for (int charNumber : digit) {
//                if (Character.isDigit(charNumber))
//                    mathSetUtil.add(charNumber);
//            }
//            System.out.println(mathSetUtil.toString());
//        }
//        MenuProgram.run();
    }
}
