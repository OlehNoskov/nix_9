package level2;

import consolemenu.MenuProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowSecondLevel {

    public static void showTasksSecondLevel(BufferedReader reader) throws IOException {
        System.out.println("===== Демонстрация заданий 2-го уровня =====");
        System.out.println("1.Поиск допустимой строки");
        System.out.println("2.Максимальная глубина бинарного дерева");
        System.out.println("0.Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");

        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        StringValid.verifyValidString(reader);
                        break;
                    case "2":
                        BinaryTree.startSearchDeepest();
                        break;
                    case "0":
                        MenuProgram.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 2 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный выбор!");
        }
    }
}