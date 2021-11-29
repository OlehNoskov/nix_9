package ua.com.alevel.calendar.createcalendar;

import ua.com.alevel.calendar.MainMenuProgram;
import ua.com.alevel.calendar.datevalid.ExaminationValidInputDataCalendar;
import ua.com.alevel.calendar.enumeration.EnumerationMonths;
import ua.com.alevel.calendar.mycalendar.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreationCalendarFormat {

    public static List<MyCalendar> myCalendarList = new ArrayList<>();
    public static int indexMyCalendar;

    public static void createCalendarFirstFormat(BufferedReader reader) {
        MyCalendar firstMyCalendar = new MyCalendar();
        try {
            String date = reader.readLine();
            String regex = "^\\d{1,2}.\\d{1,2}.\\d{1,4}$";
            if (date.matches(regex)) {
                String[] setData = date.trim().split("/");

                int[] data = new int[setData.length];
                for (int i = 0; i < setData.length; i++) {
                    data[i] = Integer.parseInt(setData[i]);
                }
                int day = data[0];
                int month = data[1];
                int year = data[2];

                if (ExaminationValidInputDataCalendar.calendarIsValid(year, month, day)) {
                    firstMyCalendar.setDay(day);
                    firstMyCalendar.setMonthNumber(month);
                    firstMyCalendar.setYear(year);
                    firstMyCalendar.setNameMonths(EnumerationMonths.getNameMonths(month));

                    myCalendarList.add(firstMyCalendar);
                    indexMyCalendar = myCalendarList.indexOf(firstMyCalendar);
                    ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
                } else
                    messageIncorrectFormatDate();
            } else
                messageIncorrectInputFormatDate();
        } catch (IOException | NumberFormatException e) {
            messageIncorrectInputFormatDate();
        }
    }

    public static void createCalendarSecondFormat(BufferedReader reader) {
        MyCalendar secondMyCalendar = new MyCalendar();
        try {
            String date = reader.readLine();
            String regex = "^\\d{1,2}.\\d{1,2}.\\d{1,4}$";

            if (date.matches(regex)) {
                String[] setData = date.trim().split("/");

                int[] data = new int[setData.length];
                for (int i = 0; i < setData.length; i++) {
                    data[i] = Integer.parseInt(setData[i]);
                }
                int day = data[1];
                int month = data[0];
                int year = data[2];

                if (ExaminationValidInputDataCalendar.calendarIsValid(year, month, day)) {
                    secondMyCalendar.setDay(day);
                    secondMyCalendar.setMonthNumber(month);
                    secondMyCalendar.setYear(year);
                    secondMyCalendar.setNameMonths(EnumerationMonths.getNameMonths(month));
                    myCalendarList.add(secondMyCalendar);
                    indexMyCalendar = myCalendarList.indexOf(secondMyCalendar);
                    ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
                } else
                    messageIncorrectFormatDate();
            } else
                messageIncorrectInputFormatDate();
        } catch (IOException | NumberFormatException e) {
            messageIncorrectInputFormatDate();
        }
    }

    public static void createCalendarThirdFormat(BufferedReader reader) {
        MyCalendar thirdMyCalendar = new MyCalendar();
        try {
            String date = reader.readLine();
            String[] dataCalendar = date.trim().split(" ");
            String month = dataCalendar[0];
            String regex = "^([А-Я])[а-я]{2,8}\s\\d{1,2}\s\\d{1,4}$";

            if (date.matches(regex)) {
                if (EnumerationMonths.getMapMonths().containsValue(month)) {
                    int day = Integer.parseInt(dataCalendar[1]);
                    int year = Integer.parseInt(dataCalendar[2]);

                    if (ExaminationValidInputDataCalendar.calendarIsValid(year,
                            EnumerationMonths.getNumberMonths(month), day)) {
                        thirdMyCalendar.setDay(day);
                        thirdMyCalendar.setNameMonths(month);
                        thirdMyCalendar.setMonthNumber(EnumerationMonths.getNumberMonths(month));
                        thirdMyCalendar.setYear(year);
                        myCalendarList.add(thirdMyCalendar);
                        indexMyCalendar = myCalendarList.indexOf(thirdMyCalendar);
                        ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
                    } else
                        messageIncorrectFormatDate();
                } else {
                    System.out.println("Некорректно введено название месяца!");
                    MainMenuProgram.run();
                }
            } else
                messageIncorrectInputFormatDate();
        } catch (IOException | NumberFormatException e) {
            messageIncorrectInputFormatDate();
        }
    }

    public static void createCalendarFourFormat(BufferedReader reader) {
        MyCalendar fourMyCalendar = new MyCalendar();
        try {
            String date = reader.readLine();
            String[] dataCalendar = date.trim().split(" ");
            String month = dataCalendar[1];
            String regex = "^(\\d{2}\s[А-Я])[а-я]{2,8}\s\\d{1,4}\s\\d{2}.\\d{2}$";

            if (date.matches(regex)) {
                if (EnumerationMonths.getMapMonths().containsValue(month)) {
                    int day = Integer.parseInt(dataCalendar[0]);
                    int year = Integer.parseInt(dataCalendar[2]);
                    String hoursAndMinutes = dataCalendar[3];
                    String[] hourAndMinute = hoursAndMinutes.split(":");
                    int hour = Integer.parseInt(hourAndMinute[0]);
                    int minute = Integer.parseInt(hourAndMinute[1]);

                    if (ExaminationValidInputDataCalendar.calendarIsValid(year,
                            EnumerationMonths.getNumberMonths(month), day)
                            && ExaminationValidInputDataCalendar.isTimeValid(hour, minute, 0, 0)) {
                        fourMyCalendar.setDay(day);
                        fourMyCalendar.setNameMonths(month);
                        fourMyCalendar.setMonthNumber(EnumerationMonths.getNumberMonths(month));
                        fourMyCalendar.setYear(year);
                        fourMyCalendar.setHour(hour);
                        fourMyCalendar.setMinutes(minute);
                        myCalendarList.add(fourMyCalendar);
                        indexMyCalendar = myCalendarList.indexOf(fourMyCalendar);
                        ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
                    } else
                        messageIncorrectFormatDate();
                } else {
                    System.out.println("Некорректно введено название месяца!");
                    MainMenuProgram.run();
                }
            } else messageIncorrectInputFormatDate();
        } catch (IOException | NumberFormatException e) {
            messageIncorrectInputFormatDate();
        }
    }

    public static void inputDataCalendarRandomFormat(BufferedReader reader) {
        try {
            MyCalendar fiveMyCalendar = new MyCalendar();
            String regex1 = "^\\d{1,2}.\\d{1,2}.\\d{1,4}$";
            String regex2 = "^.\\d{1,2}.\\d{1,4}\s\\d{2}.\\d{2}.\\d{2}.\\d{1,3}$";
            String regex3 = "^.\\d{1,2}.\s\\d{1,2}$";
            String regex4 = "^\\d{1,4}\s\\d{2}.\\d{2}$";
            String date = reader.readLine();

            if (date.matches(regex1)) {
                String[] dataCalendar = date.trim().split("/");
                int year = Integer.parseInt(dataCalendar[2]);
                int numberMonths = Integer.parseInt(dataCalendar[1]);
                int day = Integer.parseInt(dataCalendar[0]);
                if (ExaminationValidInputDataCalendar.calendarIsValid(year, numberMonths, day)) {
                    fiveMyCalendar.setDay(day);
                    fiveMyCalendar.setMonthNumber(numberMonths);
                    fiveMyCalendar.setNameMonths(EnumerationMonths.getNameMonths(numberMonths));
                    fiveMyCalendar.setYear(year);
                    myCalendarList.add(fiveMyCalendar);
                    indexMyCalendar = myCalendarList.indexOf(fiveMyCalendar);
                    ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
                } else
                    messageIncorrectFormatDate();
            }
            if (date.matches(regex2)) {
                String[] dataCalendar = date.trim().split(" ");
                String dates = dataCalendar[0];
                String[] monthsAndYears = dates.split("/");

                if (ExaminationValidInputDataCalendar
                        .calendarIsValid(Integer.parseInt(monthsAndYears[2]), Integer.parseInt(monthsAndYears[1]), 1)) {
                    fiveMyCalendar.setDay(1);
                    fiveMyCalendar.setMonthNumber(Integer.parseInt(monthsAndYears[1]));
                    fiveMyCalendar.setNameMonths(EnumerationMonths.getNameMonths(Integer.parseInt(monthsAndYears[1])));
                    fiveMyCalendar.setYear(Integer.parseInt(monthsAndYears[2]));
                    String time = dataCalendar[1];
                    String[] times = time.split(":");
                    if (ExaminationValidInputDataCalendar
                            .isTimeValid(Integer.parseInt(times[0]), Integer.parseInt(times[1])
                                    , Integer.parseInt(times[2]), Integer.parseInt(times[3]))) {

                        fiveMyCalendar.setHour(Integer.parseInt(times[0]));
                        fiveMyCalendar.setMinutes(Integer.parseInt(times[1]));
                        fiveMyCalendar.setSeconds(Integer.parseInt(times[2]));
                        fiveMyCalendar.setMilliseconds(Integer.parseInt(times[3]));
                    }
                } else {
                    messageIncorrectFormatDate();
                }
            } else
                messageIncorrectFormatDate();

            if (date.matches(regex3)) {
                String[] dataCalendar = date.trim().split(" ");
                String months = dataCalendar[0];
                String[] monthsAndYears = months.split("/");
                fiveMyCalendar.setDay(1);
                fiveMyCalendar.setMonthNumber(Integer.parseInt(monthsAndYears[1]));
                fiveMyCalendar.setNameMonths(EnumerationMonths.getNameMonths(Integer.parseInt(monthsAndYears[1])));
                fiveMyCalendar.setYear(0);

                String time = dataCalendar[1];
                String[] times = time.split(":");
                fiveMyCalendar.setHour(Integer.parseInt(times[1]));
            } else
                messageIncorrectFormatDate();

            if (date.matches(regex4)) {
                String[] dataCalendar = date.trim().split(" ");
                String year = dataCalendar[0];
                if (ExaminationValidInputDataCalendar.calendarIsValid(Integer.parseInt(year), 0, 0)) {
                    fiveMyCalendar.setMonthNumber(1);
                    fiveMyCalendar.setNameMonths(EnumerationMonths.getNameMonths(1));
                    fiveMyCalendar.setYear(Integer.parseInt(year));

                    String time = dataCalendar[1];
                    String[] times = time.split(":");

                    if (ExaminationValidInputDataCalendar.isTimeValid(Integer.parseInt(times[0]), Integer.parseInt(times[1]), 0, 0)) {
                        fiveMyCalendar.setHour(Integer.parseInt(times[0]));
                        fiveMyCalendar.setMinutes(Integer.parseInt(times[1]));
                    }
                } else messageIncorrectFormatDate();
            } else
                messageIncorrectFormatDate();

        } catch (IOException | NumberFormatException e) {
            messageIncorrectInputFormatDate();
        }
    }

    public static List<MyCalendar> getCalendarList() {
        return myCalendarList;
    }

    private static void messageIncorrectFormatDate() {
        System.out.println("Некорректно введена дата!");
        MainMenuProgram.run();
    }

    public static void messageIncorrectInputFormatDate() {
        System.out.println("Некорректный формат ввода!");
        MainMenuProgram.run();
    }
}