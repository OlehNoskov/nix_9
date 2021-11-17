package ua.com.alevel.calendar.editcalendar;

import ua.com.alevel.calendar.createcalendar.ChoiceOutputDisplayType;

import ua.com.alevel.calendar.datevalid.ExaminationValidInputDataCalendar;
import ua.com.alevel.calendar.enumeration.EnumerationDaysOfTheMonths;
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

    public static long convertedDateToSeconds(MyCalendar calendar) {
        int countNotLeapYear = calendar.getYear() - ExaminationValidInputDataCalendar.getCountLeapYear(calendar.getYear());
        int countLeapYear = ExaminationValidInputDataCalendar.getCountLeapYear(calendar.getYear());
        int countDays = countDaysInCurrentYear(calendar.getYear(), calendar.getMonthNumber(), calendar.getDay());

        long dateToSeconds = (long) countNotLeapYear * secondsPerYear + (long) countLeapYear * secondsPerIsLeapYear
                + (long) countDays * secondsPerDay + (long) calendar.getHour() * secondsPerHour
                + (long) calendar.getMinutes() * secondsPerMinute + calendar.getSeconds();

        return dateToSeconds;
    }

    public static void convertedSecondsToDate(long time) {
        int year = (int) time / secondsPerYear;
        int days = (int) time % secondsPerYear / secondsPerDay;
        int hours = (int) time % secondsPerDay % secondsPerDay / secondsPerHour;
        int minute = (int) time % secondsPerDay % secondsPerDay % secondsPerHour / secondsPerMinute;
        int seconds = (int) time % secondsPerDay % secondsPerDay % secondsPerHour % secondsPerMinute;

        System.out.println("Разница между двух дат: " + year + " лет " + days + " дней " + hours + " часов " + minute + " минут "
                + seconds + " секунд.");
    }

    private static int countDaysInCurrentYear(int year, int numberMonths, int daysInCurrentMonths) {
        int index = numberMonths - 1;
        int countDaysInCurrentYear = 0;
        if (ExaminationValidInputDataCalendar.calendarIsValid(year, numberMonths, daysInCurrentMonths)) {
            if (numberMonths != 0) {
                for (int i = 0; i < numberMonths; i++) {
                    countDaysInCurrentYear += EnumerationDaysOfTheMonths.numberOfDaysInMonths.get(i);
                }
                countDaysInCurrentYear += daysInCurrentMonths;
            }
        }
        return countDaysInCurrentYear;
    }
}