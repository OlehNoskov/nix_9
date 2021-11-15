package ua.com.alevel.calendar;

import java.io.BufferedReader;
import java.io.IOException;

public class ChoiceOutputDisplayType {

    public static void showMenuSelectDateFormatOutput(BufferedReader reader) throws IOException {
        System.out.println("В каком формате вывести дату:");
        MenuCreationDateFormat.showChoiceDateFormat();
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        choiceFirstFormat(CreationCalendarFormat.myCalendarList.get(CreationCalendarFormat.indexMyCalendar));
                        break;
                    case "2":
                        choiceSecondFormat(CreationCalendarFormat.myCalendarList.get(CreationCalendarFormat.indexMyCalendar));
                        break;
                    case "3":
                        choiceThirdFormat(CreationCalendarFormat.myCalendarList.get(CreationCalendarFormat.indexMyCalendar));
                        break;
                    case "4":
                        choiceFourFormat(CreationCalendarFormat.myCalendarList.get(CreationCalendarFormat.indexMyCalendar));
                        break;

                    default:
                        System.out.println("Введите число от 1 до 4 для выбора формата вывода!");

                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка" + e.getMessage());
        }
    }

    private static void choiceFirstFormat(MyCalendar myCalendar) {
        System.out.print("Ваша дата: ");
        System.out.println(myCalendar.getDay() + "/" + myCalendar.getMonthNumber() + "/" + myCalendar.getYear() + "\n");
        MainMenuProgram.run();
    }

    private static void choiceSecondFormat(MyCalendar myCalendar) {
        System.out.print("Ваша дата: ");
        System.out.println(myCalendar.getMonthNumber() + "/" + myCalendar.getDay() + "/" + myCalendar.getYear() + "\n");
        MainMenuProgram.run();
    }

    private static void choiceThirdFormat(MyCalendar myCalendar) {
        System.out.print("Ваша дата: ");
        System.out.println(EnumerationMonths.getNameMonths(myCalendar.getMonthNumber())
                + " " + myCalendar.getDay() + " " + myCalendar.getYear() + "\n");
        MainMenuProgram.run();
    }

    private static void choiceFourFormat(MyCalendar myCalendar) {
        System.out.print("Ваша дата: ");
        System.out.println(myCalendar.getDay() + " " + EnumerationMonths.getNameMonths(myCalendar.getMonthNumber())
                + " " + myCalendar.getYear() + " " + myCalendar.getHour() + ":" + myCalendar.getMinutes() + "\n");
        MainMenuProgram.run();
    }
}