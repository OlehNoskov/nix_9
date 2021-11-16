package ua.com.alevel.calendar.editcalendar;

import ua.com.alevel.calendar.createcalendar.ChoiceOutputDisplayType;

import ua.com.alevel.calendar.mycalendar.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;

public class ConverterDates {
    private static int second = 0;
    private static final int secondsPerMinute = 60;
    private static final int secondsPerHour = 3600;
    private static final int secondsPerDay = 86_400;
    private static final int secondsPerYear = 31_536_000;
    private static final int secondsPerIsLeapYear = 31_622_400;

    public static void convertedDatesWhenAddingDates(BufferedReader reader, MyCalendar myCalendar, int addYear, int addDays, int addHours, int addMinutes, int addSeconds, int addMilliSeconds) throws IOException {

        ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
    }
}
