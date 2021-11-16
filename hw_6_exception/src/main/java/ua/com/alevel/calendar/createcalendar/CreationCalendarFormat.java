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
                } else {
                    System.out.println("Некорректно введена дата!");
                    MainMenuProgram.run();
                }
            } else {
                System.out.println("Некорректно введены значения!");
                MainMenuProgram.run();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Неверный формат ввода!");
            MainMenuProgram.run();
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
                } else {
                    System.out.println("Некорректно введена дата!");
                    MainMenuProgram.run();
                }
            } else {
                System.out.println("Некорректно введены значения!");
                MainMenuProgram.run();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Неверный формат ввода!");
            MainMenuProgram.run();
        }
    }

    public static void createCalendarThirdFormat(BufferedReader reader) {
        MyCalendar thirdMyCalendar = new MyCalendar();
        try {
            String date = reader.readLine();
            String[] dataCalendar = date.trim().split(" ");
            String month = dataCalendar[0];
            String regex = "^([А-Я])([а-я]){2,8}\s\\d{1,2}\s\\d{1,4}$";

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
                    } else {
                        System.out.println("Некорректно введена дата!");
                        MainMenuProgram.run();
                    }
                }
            } else {
                System.out.println("Некорректно введены значения!");
                MainMenuProgram.run();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Неверный формат ввода!");
            MainMenuProgram.run();
        }
    }

    public static void createCalendarFourFormat(BufferedReader reader) {
        MyCalendar fourMyCalendar = new MyCalendar();
        try {
            String date = reader.readLine();
            String[] dataCalendar = date.trim().split(" ");
            String month = dataCalendar[1];
            String regex = "^(\\d{2}\s[А-Я])([а-я]){2,8}\s\\d{1,4}\s\\d{2}.\\d{2}$";

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
                    } else {
                        System.out.println("Некорректно введена дата!");
                        MainMenuProgram.run();
                    }
                }
            } else {
                System.out.println("Некорректно введены значения!");
                MainMenuProgram.run();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Неверный формат ввода!");
            MainMenuProgram.run();
        }
    }

    public static void inputDataCalendarRandomFormat(BufferedReader reader) {
        MyCalendar fourMyCalendar = new MyCalendar();
        try {
            String regex1 = "^\\d\s\\d{2}\s\\d{4}$";
            String regex2 = "^\\d\s\\d{2}\s\\d{2}\s\\d{2}\s\\d{2}\s\\d{3}$";
            String regex3 = "^\s\\d\s\\d{2}\s\\d{2}$";
            String regex4 = "^\\d{4}\s\\d{2}\s\\d{2}$";
            String date = reader.readLine();
            String[] dataCalendar = date.trim().split(" ");
            String month = dataCalendar[0];

            if (date.matches(regex1) | date.matches(regex2) | date.matches(regex3) | date.matches(regex4)) {
                if (EnumerationMonths.getMapMonths().containsValue(month)) {
                    int day = Integer.parseInt(dataCalendar[1]);
                    int year = Integer.parseInt(dataCalendar[2]);

                    if (ExaminationValidInputDataCalendar.calendarIsValid(year,
                            EnumerationMonths.getNumberMonths(month), day)) {
                        fourMyCalendar.setDay(day);
                        fourMyCalendar.setNameMonths(month);
                        fourMyCalendar.setMonthNumber(EnumerationMonths.getNumberMonths(month));
                        fourMyCalendar.setYear(year);
//                        fourMyCalendar.setHour();
//                        fourMyCalendar.setMinutes();

                        myCalendarList.add(fourMyCalendar);
                        indexMyCalendar = myCalendarList.indexOf(fourMyCalendar);
                        ChoiceOutputDisplayType.showMenuSelectDateFormatOutput(reader);
                    }else {
                        System.out.println("Некорректно введена дата!");
                        MainMenuProgram.run();
                    }
                }
            } else {
                System.out.println("Некорректно введены значения!");
                MainMenuProgram.run();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Неверный формат ввода!");
            MainMenuProgram.run();
        }
    }

    public static List<MyCalendar> getCalendarList() {
        return myCalendarList;
    }
}