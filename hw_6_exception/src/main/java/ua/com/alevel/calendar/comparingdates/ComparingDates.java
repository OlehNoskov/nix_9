package ua.com.alevel.calendar.comparingdates;

import ua.com.alevel.calendar.MainMenuProgram;
import ua.com.alevel.calendar.createcalendar.CreationCalendarFormat;
import ua.com.alevel.calendar.createcalendar.MenuCreationDateFormat;
import ua.com.alevel.calendar.mycalendar.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparingDates {

    public static void showMenuCompareDates(BufferedReader reader) {
        showMenu();
        String menu;
        try {
            while ((menu = reader.readLine()) != null) {
                switch (menu) {
                    case "1":
                        showMenuChoiceDates(reader);
                        break;
                    case "2":
                        MenuCreationDateFormat.showMenuCreateCalendar(reader);
                        break;
                    case "0":
                        MainMenuProgram.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 2 для запуска программы");
                        System.out.println("Для выхода из программы введите 0");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Некорректно введены данные!");
            showMenuCompareDates(reader);
        }
    }

    private static void showMenu() {
        System.out.println("=== Меню сравнения дат ===");
        System.out.println("1.Сравнить из списка созданных");
        System.out.println("2.Создать новую дату");
        System.out.println("0.Выход в Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void showMenuChoiceDates(BufferedReader reader) throws IOException, NumberFormatException {
        System.out.println("=== Меню сравнения даты ===");
        List<MyCalendar> selectedMyCalendarList = new ArrayList<>();
        if (CreationCalendarFormat.myCalendarList.size() != 0) {
            System.out.println("Нажмите 1 для вывода список сравения дат по убыванию:");
            System.out.println("Нажмите 2 для вывода список сравения дат по возростанию:");

            String choiceSortDates = reader.readLine();
            switch (choiceSortDates) {
                case "1":
                    sortAscDates();
                    break;
                case "2":
                    sortDescDates();
                    break;
                case "0":
                    MainMenuProgram.run();
                    break;
                default:
                    System.out.println("Введите число от 1 до 2 для запуска программы");
                    System.out.println("Для выхода в Главное Меню введите 0");
            }
        } else {
            System.out.println("На данный момент список MyCalendar пустой!");
            MainMenuProgram.run();
        }
    }

    private static void sortDescDates() {
        System.out.println("Ваш список дат по убыванию!");
        System.out.println(getSortDescListDates());
        MainMenuProgram.run();
    }

    private static void sortAscDates() {
        System.out.println("Ваш список дат по возростанию!");
        System.out.println(getSortAscListDates());
        MainMenuProgram.run();
    }

    private static List<MyCalendar> getSortDescListDates() {
        List<MyCalendar> calendars = CreationCalendarFormat.getCalendarList();
        calendars.sort(Comparator.comparing(MyCalendar::getYear)
                .thenComparing(MyCalendar::getMonthNumber).thenComparing(MyCalendar::getDay)
                .thenComparing(MyCalendar::getHour)
                .thenComparing(MyCalendar::getMinutes));
        return calendars;
    }

    private static List<MyCalendar> getSortAscListDates() {
        List<MyCalendar> calendars = CreationCalendarFormat.getCalendarList();
        calendars.sort(Comparator.comparing(MyCalendar::getYear)
                .thenComparing(MyCalendar::getMonthNumber).thenComparing(MyCalendar::getDay)
                .thenComparing(MyCalendar::getHour)
                .thenComparing(MyCalendar::getMinutes).reversed());
        return calendars;
    }
}