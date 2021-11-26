package ua.com.alevel.consolemenu;

import ua.com.alevel.cities.ShortestWays;
import ua.com.alevel.listdates.ListDates;
import ua.com.alevel.nameunique.UniqueName;

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
                        ListDates.showListInputAndOutputDates();
                        break;
                    case "2":
                        UniqueName.showFirstUniqueName();
                        break;
                    case "3":
                        new ShortestWays().run();
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
        System.out.println("===== Модуль №2 =====");
        System.out.println("1.Демонстрация списка дат");
        System.out.println("2.Нахождение первого уникального имени");
        System.out.println("3.Расстояние между городами");
        System.out.println("0.Выход из программы");
        System.out.println();
        System.out.println("Выберите один из предложенных вариантов:");
    }
}