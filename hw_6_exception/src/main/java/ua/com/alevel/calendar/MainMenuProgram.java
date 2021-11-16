package ua.com.alevel.calendar;

import ua.com.alevel.calendar.createcalendar.MenuCreationDateFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenuProgram {
    public static void run() {
        showMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu;
        try {
            while ((menu = reader.readLine()) != null) {
                switch (menu) {
                    case "1":
                        MenuCreationDateFormat.showMenuCreateCalendar(reader);
                        break;
                    case "2":

                        break;
                    case "3":
                        ComparingDates.showMenuCompareDates(reader);
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Введите число от 1 до 2 для запуска программы");
                        System.out.println("Для выхода из программы введите 0");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("===== Консольное приложение Calendar =====");
        System.out.println("1.Создать обьект класса Calendar");
        System.out.println("2.Редактирование обьекта Calendar");
        System.out.println("3.Сравнение даты");
        System.out.println("0.Выход из программы");
        System.out.println();
        System.out.println("Выберите один из предложенных вариантов:");
    }
}