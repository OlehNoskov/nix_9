package ua.com.alevel.calendar.differencedates;

import ua.com.alevel.calendar.MainMenuProgram;
import ua.com.alevel.calendar.createcalendar.CreationCalendarFormat;
import ua.com.alevel.calendar.editcalendar.ConverterDates;
import ua.com.alevel.calendar.mycalendar.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DifferenceBetweenDates {

    public static void showMenuDifferenceBetweenDates(BufferedReader reader) throws IOException {
        System.out.println("=== Меню сравнения 2-х дат ===");

        List<MyCalendar> selectedMyCalendarList = new ArrayList<>();
        if (CreationCalendarFormat.myCalendarList.size() != 0) {
            System.out.println("Выберите 1-ый Date от 0 до " + (CreationCalendarFormat.myCalendarList.size() - 1) + ":");
            int choice = Integer.parseInt(reader.readLine());

            if (choice >= 0 && choice < CreationCalendarFormat.myCalendarList.size()) {
                selectedMyCalendarList.add(CreationCalendarFormat.myCalendarList.get(choice));
                System.out.println("Выберите 2-ой Date от 0 до " + (CreationCalendarFormat.myCalendarList.size() - 1) + ":");
                int secondChoice = Integer.parseInt(reader.readLine());

                if (secondChoice >= 0 && secondChoice < CreationCalendarFormat.myCalendarList.size()) {
                    if (choice == secondChoice) {
                        System.out.println("Даты равны!");
                        MainMenuProgram.run();
                    } else {
                        selectedMyCalendarList.add(CreationCalendarFormat.myCalendarList.get(secondChoice));
                        getDifferenceBetweenDates(selectedMyCalendarList.get(0), selectedMyCalendarList.get(1));
                    }
                } else {
                    CreationCalendarFormat.messageIncorrectInputFormatDate();
                }
            } else {
                CreationCalendarFormat.messageIncorrectInputFormatDate();
            }
        } else {
            System.out.println("На данный момент список MyCalendar пустой!");
            MainMenuProgram.run();
        }
    }

    private static void getDifferenceBetweenDates(MyCalendar myCalendar1, MyCalendar myCalendar2) {
        System.out.println("Разница в дате:");
        long firstTime = ConverterDates.convertedDateToSeconds(myCalendar1);
        long secondTime = ConverterDates.convertedDateToSeconds(myCalendar2);
        long resultTimeToSeconds = firstTime - secondTime;

        ConverterDates.convertedSecondsToDate(resultTimeToSeconds);
        MainMenuProgram.run();
    }
}