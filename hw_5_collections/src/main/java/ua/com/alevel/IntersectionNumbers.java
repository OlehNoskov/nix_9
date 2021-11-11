package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntersectionNumbers {
    public static void showIntersectionNumbers(BufferedReader reader) {
        System.out.println("=== Нахождение общих чисел ===" + "\n");
        System.out.println("1.Нахождение общих чисел 2-ух MathSet");
        System.out.println("2.Нахождение общих чисел нескольких MathSet");
        System.out.println("0. Выход в Меню редактирования MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":

                        break;
                    case "2":

                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 2 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }
}