package ua.com.alevel.calendar.editcalendar;

import ua.com.alevel.calendar.MainMenuProgram;
import ua.com.alevel.calendar.createcalendar.CreationCalendarFormat;
import ua.com.alevel.calendar.createcalendar.MenuCreationDateFormat;
import ua.com.alevel.calendar.datevalid.ExaminationValidInputDataCalendar;
import ua.com.alevel.calendar.mycalendar.MyCalendar;

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
            System.out.println("Выберите дату от 0 до " + (CreationCalendarFormat.myCalendarList.size() - 1) + ":");
            int choice = Integer.parseInt(reader.readLine());
            if (choice >= 0 && choice < CreationCalendarFormat.myCalendarList.size()) {
                selectAdditionAndSubtractionDates(reader,CreationCalendarFormat.myCalendarList.get(choice));
            } else {
                System.out.println("Дата с данным индексом отсутствует!");
                showMenuEditCalendar(reader);
            }
        } else {
            System.out.println("На данный момент список дат пуст!");
            MainMenuProgram.run();
        }
    }

    private static void selectAdditionAndSubtractionDates(BufferedReader reader, MyCalendar myCalendar) throws IOException {
        System.out.println("=== Выберите действие для работы с датой ===");
        System.out.println("1. Прибавить к дате год, дни, часы, минуты, секунды и миллисекунды.");
        System.out.println("2. Вычесть из даты год, дни, часы, минуты, секунды и миллисекунды.");
        System.out.println("0. Выход в Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");
        System.out.println("");
        String menu = reader.readLine();
        switch (menu) {
            case "1":
                additionDates(reader,myCalendar);
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

    private static void additionDates(BufferedReader reader, MyCalendar myCalendar) throws IOException, NumberFormatException {
        System.out.print("Введите сколько добавить лет к текущей дате:");
        int addYears = Integer.parseInt(reader.readLine());
        System.out.print("Введите сколько добавить дней к текущей дате:");
        int addDays = Integer.parseInt(reader.readLine());

        if (ExaminationValidInputDataCalendar.IsValidInputYearAndDay(addYears, addDays)) {

            System.out.print("Введите сколько добавить часов к текущей дате:");
            int addHours = Integer.parseInt(reader.readLine());
            System.out.print("Введите сколько добавить минут к текущей дате:");
            int addMinutes = Integer.parseInt(reader.readLine());
            System.out.print("Введите сколько добавить секунд к текущей дате:");
            int addSeconds = Integer.parseInt(reader.readLine());
            System.out.print("Введите сколько добавить миллисекунд к текущей дате:");
            int addMilliseconds = Integer.parseInt(reader.readLine());

            if (ExaminationValidInputDataCalendar.IsValidInputTime(addHours, addMinutes, addSeconds, addMilliseconds)) {
                ConverterDates.convertedDatesWhenAddingDates(reader,myCalendar,addYears,addDays,addHours,addMinutes,addSeconds,addMilliseconds);
            } else {
                System.out.println("Введены некорректные значения времени!");
                showMenuEditCalendar(reader);
            }
        } else {
            System.out.println("Введены некорректные значения даты!");
            showMenuEditCalendar(reader);
        }
    }

    private static void subtractionDates(BufferedReader reader) {

    }
}