package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MenuProgram {

    public static void run() {
        showMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu;
        try {
            while ((menu = reader.readLine()) != null) {
                switch (menu) {
                    case "1":
                        new FirstTask().sumNumbers(reader);
                        break;
                    case "2":
                        new SecondTask().countCharAndShowResult(reader);
                        break;
                    case "3":
                        new ThirdTask().showTimeLessonsEnd(reader);
                        break;
                    case "4":
                        System.exit(4);
                        break;
                    default:
                        System.out.println("Введите число от 1 до 3 для запуска программы");
                        System.out.println("Для выхода из программы введите 4");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Данная программа выполняет 3 действия:");
        System.out.println("1.Запуск 1-го домашнего задания");
        System.out.println("2.Запуск 2-го домашнего задания");
        System.out.println("3.Запуск 3-го домашнего задания");
        System.out.println("4.Выход из программы");
        System.out.println();
        System.out.println("Выберите один из предложенных вариантов:");
    }
}



