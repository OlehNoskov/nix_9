package ua.com.alevel.calendar.editcalendar;

import ua.com.alevel.calendar.MainMenuProgram;
import ua.com.alevel.calendar.createcalendar.ChoiceOutputDisplayType;
import ua.com.alevel.calendar.createcalendar.CreationCalendarFormat;
import ua.com.alevel.calendar.enumeration.EnumerationDaysOfTheMonths;
import ua.com.alevel.calendar.mycalendar.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;

public class SubtractionDate {

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
                        removeMilliSeconds(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "2":
                        removeSeconds(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "3":
                        removeMinutes(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "4":
                        removeHours(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "5":
                        removeDays(reader, CreationCalendarFormat.myCalendarList.get(choice));
                        break;
                    case "6":
                        removeYears(reader, CreationCalendarFormat.myCalendarList.get(choice));
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
        System.out.println("1.Вычесть миллисекунды");
        System.out.println("2.Вычесть секунды");
        System.out.println("3.Вычесть минуты");
        System.out.println("4.Вычесть час");
        System.out.println("5.Вычесть день");
        System.out.println("6.Вычесть год");
        System.out.println("0.Выход в Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void removeMilliSeconds(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько миллисекунд вычесть:");
        int removeMilliseconds = Integer.parseInt(reader.readLine());

        if (removeMilliseconds >= 0 && removeMilliseconds <= 1000) {
            if (calendar.getMilliseconds() - removeMilliseconds > 0) {
                calendar.setMilliseconds(calendar.getMilliseconds() - removeMilliseconds);
            } else
                calendar.setSeconds(calendar.getSeconds() - 1);
            calendar.setMilliseconds(calendar.getMilliseconds() - removeMilliseconds + 1000);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете вычесть от 0 до 1000");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void removeSeconds(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько секунд вычесть:");
        int removeSeconds = Integer.parseInt(reader.readLine());

        if (removeSeconds >= 0 && removeSeconds <= 60) {
            if (calendar.getSeconds() - removeSeconds > 0) {
                calendar.setSeconds(calendar.getSeconds() - removeSeconds);
            } else
                calendar.setMinutes(calendar.getMinutes() - 1);
            calendar.setSeconds(calendar.getSeconds() - removeSeconds + 60);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете вычесть от 0 до 60 ");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void removeMinutes(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько минут вычесть:");
        int removeMinutes = Integer.parseInt(reader.readLine());

        if (removeMinutes >= 0 && removeMinutes <= 60) {
            if (calendar.getMinutes() - removeMinutes > 0) {
                calendar.setMinutes(calendar.getMinutes() - removeMinutes);
            } else
                calendar.setHour(calendar.getHour() - 1);
            calendar.setMinutes(calendar.getMinutes() - removeMinutes + 60);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете вычесть от 0 до 60");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void removeHours(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько часов вычесть:");
        int removeHours = Integer.parseInt(reader.readLine());

        if (removeHours >= 0 && removeHours <= 24) {
            if (calendar.getHour() - removeHours > 0) {
                calendar.setHour(calendar.getHour() - removeHours);
            } else
                calendar.setDay(calendar.getDay() - 1);
            calendar.setHour(calendar.getHour() - removeHours + 24);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете вычесть от 0 до 24");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void removeDays(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько дней вычесть:");
        int removeDays = Integer.parseInt(reader.readLine());

        if (removeDays >= 0 && removeDays <= 31) {
            int countCurrentMonths = EnumerationDaysOfTheMonths.numberOfDaysInMonths.get(calendar.getMonthNumber() - 1);
            if (calendar.getDay() - removeDays > 0) {
                calendar.setDay(calendar.getDay() - removeDays);
            } else
                calendar.setMonthNumber(calendar.getMonthNumber() - 1);
            calendar.setDay(calendar.getDay() - removeDays + EnumerationDaysOfTheMonths
                    .numberOfDaysInMonths.get(countCurrentMonths - 1));
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете вычесть от 0 до 31 дней!");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }

    private static void removeYears(BufferedReader reader, MyCalendar calendar) throws IOException {
        System.out.println("Сколько лет вычесть:");
        int removeYears = Integer.parseInt(reader.readLine());

        if (removeYears >= 0 && (calendar.getYear() - removeYears) > 0) {
            calendar.setYear(calendar.getYear() - removeYears);
            ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
        } else {
            System.out.println("Вы можете вычесть от 0 до " + calendar.getYear() + "!");
            EditCalendar.selectAdditionAndSubtractionDates(reader);
        }
    }
}