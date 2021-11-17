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