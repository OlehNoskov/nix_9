package level3;

import consolemenu.MenuProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowThirdLevel {

    public  void showTasksThirdLevel(BufferedReader reader) throws IOException {
        System.out.println("===== Демонстрация заданий 3-го уровня =====");
        System.out.println("1.Игра жизни.");
        System.out.println("0.Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        new GameOfLife().go();
                        break;
                    case "0":
                        MenuProgram.run();
                        break;
                    default:
                        System.out.println("Введите число 1 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный выбор!");
        }
    }
}