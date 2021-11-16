package ua.com.alevel.calendar;

public class MyCalendar {
    private int year;
    private int monthNumber;
    private String nameMonths;
    private int day;
    private int hour;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public MyCalendar() {
    }

    public MyCalendar(int year, int monthNumber, String nameMonths, int day, int hour, int minutes, int seconds, int milliseconds) {
        this.year = year;
        this.monthNumber = monthNumber;
        this.nameMonths = nameMonths;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getNameMonths() {
        return nameMonths;
    }

    public void setNameMonths(String nameMonths) {
        this.nameMonths = nameMonths;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    public String toString() {
        return "Дата: " + day + " " + nameMonths + " " + year + "г. " + hour + ":" +
                minutes + ":" + seconds + ":" + milliseconds + "\n";
    }
}
//    private static LocalDate create(int year, int month, int dayOfMonth) {
//        if (dayOfMonth > 28) {
//            int dom = 31;
//            switch (month) {
//                case 2:
//                    dom = (IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28);
//                    break;
//                case 4:
//                case 6:
//                case 9:
//                case 11:
//                    dom = 30;
//                    break;
//            }
//            if (dayOfMonth > dom) {
//                if (dayOfMonth == 29) {
//                    throw new DateTimeException("Invalid date 'February 29' as '" + year + "' is not a leap year");
//                } else {
//                    throw new DateTimeException("Invalid date '" + Month.of(month).name() + " " + dayOfMonth + "'");
//                }
//            }
//        }
//        return new LocalDate(year, month, dayOfMonth);
//    }


//public boolean isLeapYear(long prolepticYear) {
//    return ((prolepticYear & 3) == 0) && ((prolepticYear % 100) != 0 || (prolepticYear % 400) == 0);
//}

//    public int lengthOfMonth() {
//        switch (month) {
//            case 2:
//                return (isLeapYear() ? 29 : 28);
//            case 4:
//            case 6:
//            case 9:
//            case 11:
//                return 30;
//            default:
//                return 31;
//        }

//    public int lengthOfYear() {
//        return (isLeapYear() ? 366 : 365);
//    }

//    public LocalDate plusMonths(long monthsToAdd) {
//        if (monthsToAdd == 0) {
//            return this;
//        }
//        long monthCount = year * 12L + (month - 1);
//        long calcMonths = monthCount + monthsToAdd;  // safe overflow
//        int newYear = YEAR.checkValidIntValue(Math.floorDiv(calcMonths, 12));
//        int newMonth = Math.floorMod(calcMonths, 12) + 1;
//        return resolvePreviousValid(newYear, newMonth, day);
//    }

//    public LocalDate plusDays(long daysToAdd) {
//        if (daysToAdd == 0) {
//            return this;
//        }
//        long dom = day + daysToAdd;
//        if (dom > 0) {
//            if (dom <= 28) {
//                return new LocalDate(year, month, (int) dom);
//            } else if (dom <= 59) { // 59th Jan is 28th Feb, 59th Feb is 31st Mar
//                long monthLen = lengthOfMonth();
//                if (dom <= monthLen) {
//                    return new LocalDate(year, month, (int) dom);
//                } else if (month < 12) {
//                    return new LocalDate(year, month + 1, (int) (dom - monthLen));
//                } else {
//                    YEAR.checkValidValue(year + 1);
//                    return new LocalDate(year + 1, 1, (int) (dom - monthLen));
//                }
//            }
//        }
//
//        long mjDay = Math.addExact(toEpochDay(), daysToAdd);
//        return LocalDate.ofEpochDay(mjDay);
//    }

//    public LocalDate minusYears(long yearsToSubtract) {
//        return (yearsToSubtract == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-yearsToSubtract));
//    }
//
//    public LocalDate minusMonths(long monthsToSubtract) {
//        return (monthsToSubtract == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-monthsToSubtract));
//    }

//    public LocalDate minusDays(long daysToSubtract) {
//        return (daysToSubtract == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-daysToSubtract));
//    }

//    public static boolean isLeap(long year) {
//        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
//    }
