package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeletedNumbers {
    public static void showDeletedNumbers(BufferedReader reader) {
        System.out.println("=== Вырезать и удалить MathSet ===" + "\n");
        System.out.println("1.Вырезать данные из MathSet");
        System.out.println("2.Очистить всё");
        System.out.println("3.Очистить массив Numbers");
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
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 3 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }
}
