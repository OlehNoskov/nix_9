package ua.com.alevel.calendar.enumeration;

import java.util.*;

public class EnumerationDaysOfTheMonths {

    public final static List<Integer> numberOfDaysInMonths = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    public static TreeMap<Integer, Integer> getMapMonthsOfDays() {

        Map<Integer, Integer> daysOfTheMonths = new TreeMap<>();

        daysOfTheMonths.put(1, 31);
        daysOfTheMonths.put(2, 28);
        daysOfTheMonths.put(3, 31);
        daysOfTheMonths.put(4, 30);
        daysOfTheMonths.put(5, 31);
        daysOfTheMonths.put(6, 30);
        daysOfTheMonths.put(7, 31);
        daysOfTheMonths.put(8, 31);
        daysOfTheMonths.put(9, 30);
        daysOfTheMonths.put(10, 31);
        daysOfTheMonths.put(11, 30);
        daysOfTheMonths.put(12, 31);

        return getMapMonthsOfDays();
    }
}