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

    public static boolean IsValidInputYearAndDay(int year, int day) {
        if ((year < 0 || year > 9999) || (day < 0 || day > 365)) {
            return false;
        }
        return true;
    }

    public static boolean IsValidInputTime(int hour, int minutes, int seconds, int milliseconds) {
        if ((hour < 0 || hour > 24) || (minutes < 0 || minutes > 60) || (seconds < 0 || seconds > 60)
                || (milliseconds < 0 || milliseconds > 999)) {
            return false;
        }
        return true;
    }


    public static boolean isTimeValid(int hour, int minutes, int second, int milliseconds) {
        if (hour >= 0 && hour <= 24) {
            return true;
        } else if (minutes >= 0 && minutes <= 60) {
            return true;
        } else if (second >= 0 && second <= 60) {
            return true;
        } else if (milliseconds >= 0 && milliseconds <= 999) {
            return true;
        }
        return false;
    }
}