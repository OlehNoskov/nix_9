package consolemenu;

import level1.ShowFirstLevel;
import level2.ShowSecondLevel;
import level3.ShowThirdLevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuProgram {

    public static void run() {
        showMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu;
        try {
            while ((menu = reader.readLine()) != null) {
                switch (menu) {
                    case "1":
                        new ShowFirstLevel().showTasksFirstLevel(reader);
                        break;
                    case "2":
                        new ShowSecondLevel().showTasksSecondLevel(reader);
                        break;
                    case "3":
                        new ShowThirdLevel().showTasksThirdLevel(reader);
                        break;
                    case "0":
                       System.exit(0);
                        break;
                    default:
                        System.out.println("Введите число от 1 до 3 для запуска программы");
                        System.out.println("Для выхода из программы введите 0");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Данная программа выполняет 3 действия:");
        System.out.println("1.Демонстрация заданий 1-го уровня");
        System.out.println("2.Демонстрация заданий 2-го уровня");
        System.out.println("3.Демонстрация заданий 3-го уровня");
        System.out.println("0.Выход из программы");
        System.out.println();
        System.out.println("Выберите один из предложенных вариантов:");
    }
}
