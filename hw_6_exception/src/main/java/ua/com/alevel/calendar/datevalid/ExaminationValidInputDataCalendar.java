package ua.com.alevel.calendar.datevalid;

public class ExaminationValidInputDataCalendar {

    public static boolean isLeapYear(long year) {
        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    public static boolean calendarIsValid(int year, int monthNumber, int day) {
        if (year < 0 || year > 9999) {
            return false;
        }
        switch (monthNumber) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return (day >= 1 && day <= 31);
            case 4:
            case 6:
            case 9:
            case 11:
                return (day >= 1 && day <= 30);
            case 2:
                if (isLeapYear(year))
                    return (day >= 1 && day <= 29);
                else return (day >= 1 && day <= 28);
            default:
                return false;
        }
    }

    public static boolean isTimeValid(int hour, int minutes, int second, int milliseconds) {
        if (hour >= 0 && hour <= 24) {
            return true;
        } else if (minutes >= 0 && minutes <= 60) {
            return true;
        } else if (second >= 0 && second <= 60) {
            return true;
        } else if (milliseconds >= 0 && milliseconds <= 1000) {
            return true;
        }
        return false;
    }

    public static int getCountLeapYear(int year) {
        int countLeapYear = 0;
        for (int i = 0; i <= year; i++) {
            if (isLeapYear(i))
                countLeapYear++;
        }
        return countLeapYear;
    }
}