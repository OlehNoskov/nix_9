package ua.com.alevel;

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
                    SetNumbers.createNewMathSet(reader);
                        break;
                    case "2":

                        break;
                    case "3":

                        break;
                    case "4":

                        break;
                    case "5":

                        break;
                    case "6":

                        break;
                    case "7":

                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Введите число от 1 до 7 для запуска программы");
                        System.out.println("Для выхода из программы введите 0");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Home Work Collections"+"\n");
        System.out.println("1.Создание массива уникальных чисел");
        System.out.println("2.Добавить числа в массив");
        System.out.println("3.Найти общие числа массивов");
        System.out.println("4.Сортировка чисел");
        System.out.println("5.Нахождение чисел по заданным критериям");
        System.out.println("6.Преобразование чисел в строку");
        System.out.println("7.Удаление чисел из массива");
        System.out.println("Выберите один из предложенных вариантов:");
    }
}