package ua.com.alevel.calendar.editcalendar;

import ua.com.alevel.calendar.MainMenuProgram;
import ua.com.alevel.calendar.createcalendar.CreationCalendarFormat;
import ua.com.alevel.calendar.createcalendar.MenuCreationDateFormat;

import java.io.BufferedReader;
import java.io.IOException;

public class EditCalendar {

    public static void showMenuEditCalendar(BufferedReader reader) {
        showMenu();
        String menu;
        try {
            while ((menu = reader.readLine()) != null) {
                switch (menu) {
                    case "1":
                        selectDate(reader);
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
            MainMenuProgram.run();
        }
    }

    private static void showMenu() {
        System.out.println("=== Меню редактирования даты ===");
        System.out.println("1.Выбрать дату из списка созданных");
        System.out.println("2.Создать новую дату");
        System.out.println("0.Выход в Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void selectDate(BufferedReader reader) throws IOException, NumberFormatException {
        System.out.println("=== Меню выбора даты ===");
        if (CreationCalendarFormat.myCalendarList.size() != 0) {
            System.out.println("Выберите дату от 0 до " + CreationCalendarFormat.myCalendarList.size() + ":");
            int choice = Integer.parseInt(reader.readLine());
            if (choice > 0 && choice < CreationCalendarFormat.myCalendarList.size()) {
                selectAdditionAndSubtractionDates(reader);
            } else {
                System.out.println("Списсок с данным индексом отсутствует!");
                showMenuEditCalendar(reader);
            }
        } else {
            System.out.println("На данный момент список дат пуст!");
            MainMenuProgram.run();
        }
    }

    private static void selectAdditionAndSubtractionDates(BufferedReader reader) throws IOException {
        String menu = reader.readLine();
        switch (menu) {
            case "1":
                additionDates(reader);
                break;
            case "2":
                subtractionDates(reader);
                break;
            case "0":
                MainMenuProgram.run();
                break;
            default:
                System.out.println("Введите число от 1 до 2 для запуска программы");
                System.out.println("Для выхода в Главное Меню введите 0");
        }
    }

    private static void additionDates(BufferedReader reader) {

    }

    private static void subtractionDates(BufferedReader reader) {

    }
}