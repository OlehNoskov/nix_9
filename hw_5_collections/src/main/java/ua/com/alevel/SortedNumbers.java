package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortedNumbers {
    public static void showSortedNumbers(BufferedReader reader) {
        System.out.println("=== Сортировка MathSet ===" + "\n");
        System.out.println("1.Сортировка MathSet на убывание");
        System.out.println("2.Сортировка MathSet на возростание");
        System.out.println("3.Сортировка MathSet на убывание по индексу и до конца массива!");
        System.out.println("4.Сортировка MathSet на возростанию по индексу и до конца массива!");
        System.out.println("5.Сортировка MathSet на убывание от № и до №.");
        System.out.println("5.Сортировка MathSet на возростанию от № и до №.");
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
                    case "3":

                        break;
                    case "4":

                        break;
                    case "5":

                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 5 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }
}