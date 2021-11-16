package ua.com.alevel.calendar.mycalendar;

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
                minutes + ":" + seconds + ":" + milliseconds;
    }
}

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
//
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
//
//    public LocalDate minusYears(long yearsToSubtract) {
//        return (yearsToSubtract == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-yearsToSubtract));
//    }
//
//    public LocalDate minusMonths(long monthsToSubtract) {
//        return (monthsToSubtract == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-monthsToSubtract));
//    }
//
//    public LocalDate minusDays(long daysToSubtract) {
//        return (daysToSubtract == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-daysToSubtract));
//    }
