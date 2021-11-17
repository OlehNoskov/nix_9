package ua.com.alevel.calendar.editcalendar;

import ua.com.alevel.calendar.MainMenuProgram;
import ua.com.alevel.calendar.createcalendar.ChoiceOutputDisplayType;
import ua.com.alevel.calendar.createcalendar.CreationCalendarFormat;
import ua.com.alevel.calendar.enumeration.EnumerationDaysOfTheMonths;
import ua.com.alevel.calendar.mycalendar.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;

public class AddingTime {

    public static void selectDate(BufferedReader reader) throws IOException, NumberFormatException {
        System.out.println("=== Меню выбора даты ===");
        if (CreationCalendarFormat.myCalendarList.size() != 0) {
            System.out.println("Выберите дату от 0 до " + (CreationCalendarFormat.myCalendarList.size() - 1) + ":");
            int choice = Integer.parseInt(reader.readLine());
            if (choice >= 0 && choice < CreationCalendarFormat.myCalendarList.size()) {
                showMenuAddingTime();
                String menuChoice = reader.readLine();
                switch (menuChoice) {
                    case "1":
                        addMilliSeconds(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "2":
                        addSeconds(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "3":
                        addMinutes(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "4":
                        addHours(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "5":
                        addDays(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "6":
                        addYears(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "0":
                        MainMenuProgram.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 6 для запуска программы");
                        System.out.println("Для выхода в Главное Меню введите 0");
                }
            } else {
                System.out.println("Дата с данным индексом отсутствует!");
                EditCalendar.selectAdditionAndSubtractionDates(reader);
            }
        } else {
            System.out.println("На данный момент список дат пуст!");
            MainMenuProgram.run();
        }
    }

    private static void showMenuAddingTime() {
        System.out.println("1.Добавить миллисекунды");
        System.out.println("2.Добавить секунды");
        System.out.println("3.Добавить минуты");
        System.out.println("4.Добавить час");
        System.out.println("5.Добавить день");
        System.out.println("6.Добавить год");
        System.out.println("0.Выход в Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void addMilliSeconds(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько миллисекунд добавить:");
        int addMilliseconds = Integer.parseInt(reader.readLine());
        if (addMilliseconds >= 0 && addMilliseconds <= 1000) {
            if (calendar.getMilliseconds() + addMilliseconds < 1000) {
                calendar.setMilliseconds(calendar.getMilliseconds() + addMilliseconds);
            } else
                calendar.setSeconds(calendar.getSeconds() + 1);
            calendar.setMilliseconds(calendar.getMilliseconds() + addMilliseconds - 1000);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете добавить от 0 до 1000");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void addSeconds(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько секунд добавить:");
        int addSeconds = Integer.parseInt(reader.readLine());
        if (addSeconds >= 0 && addSeconds <= 60) {
            if (calendar.getSeconds() + addSeconds < 60) {
                calendar.setSeconds(calendar.getSeconds() + addSeconds);
            } else
                calendar.setMinutes(calendar.getMinutes() + 1);
            calendar.setSeconds(calendar.getSeconds() + addSeconds - 60);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете добавить от 0 до 60 ");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void addMinutes(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько минут добавить:");
        int addMinutes = Integer.parseInt(reader.readLine());
        if (addMinutes >= 0 && addMinutes <= 60) {
            if (calendar.getMinutes() + addMinutes < 60) {
                calendar.setMinutes(calendar.getMinutes() + addMinutes);
            } else
                calendar.setHour(calendar.getHour() + 1);
            calendar.setMinutes(calendar.getMinutes() + addMinutes - 60);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете добавить от 0 до 60");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void addHours(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько часов добавить:");
        int addHours = Integer.parseInt(reader.readLine());
        if (addHours >= 0 && addHours <= 24) {
            if (calendar.getHour() + addHours < 24) {
                calendar.setHour(calendar.getHour() + addHours);
            } else
                calendar.setDay(calendar.getDay() + 1);
            calendar.setHour(calendar.getHour() + addHours - 24);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете добавить от 0 до 24");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void addDays(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько дней добавить:");
        int addDays = Integer.parseInt(reader.readLine());

        if (addDays >= 0 && addDays <= 31) {
            int countCurrentMonths = EnumerationDaysOfTheMonths.numberOfDaysInMonths.get(calendar.getMonthNumber() - 1);
            if (calendar.getDay() + addDays < countCurrentMonths) {
                calendar.setDay(calendar.getDay() + addDays);
            } else
                calendar.setMonthNumber(calendar.getMonthNumber() + 1);
            calendar.setDay(calendar.getDay() + addDays - countCurrentMonths);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете добавить от 0 до 31 дней!");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void addYears(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько лет добавить:");
        int addYears = Integer.parseInt(reader.readLine());

        if (addYears >= 0 && addYears < (9999 - calendar.getYear())) {
            calendar.setYear(calendar.getYear() + addYears);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете добавить от 0 до " + (9999 - calendar.getYear()) + "!");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }
}