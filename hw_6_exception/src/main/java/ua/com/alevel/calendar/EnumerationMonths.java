package ua.com.alevel.calendar;

import java.util.Map;
import java.util.TreeMap;

public class EnumerationMonths {

    public static TreeMap<Integer, String> getMapMonths() {

        Map<Integer, String> monthsNamesAndNumbersMonths = new TreeMap<>();

        monthsNamesAndNumbersMonths.put(1, "Январь");
        monthsNamesAndNumbersMonths.put(2, "Февраль");
        monthsNamesAndNumbersMonths.put(3, "Март");
        monthsNamesAndNumbersMonths.put(4, "Апрель");
        monthsNamesAndNumbersMonths.put(5, "Mай");
        monthsNamesAndNumbersMonths.put(6, "Июнь");
        monthsNamesAndNumbersMonths.put(7, "Июль");
        monthsNamesAndNumbersMonths.put(8, "Август");
        monthsNamesAndNumbersMonths.put(9, "Сентябрь");
        monthsNamesAndNumbersMonths.put(10, "Октябрь");
        monthsNamesAndNumbersMonths.put(11, "Ноябрь");
        monthsNamesAndNumbersMonths.put(12, "Декабрь");

        return (TreeMap<Integer, String>) monthsNamesAndNumbersMonths;
    }

    public static String getNameMonths(int numberMonths) {
        String nameMonths = null;
        for (Map.Entry<Integer, String> entry : EnumerationMonths.getMapMonths().entrySet()) {
            if (entry.getKey().equals(numberMonths)) {
                nameMonths = entry.getValue();
            }
        }
        return nameMonths;
    }

    public static int getNumberMonths(String nameMonths) {
        int numberMonths = 0;
        for (Map.Entry<Integer, String> entry : EnumerationMonths.getMapMonths().entrySet()) {
            if (entry.getValue().equals(nameMonths)) {
                numberMonths = entry.getKey();
            }
        }
        return numberMonths;
    }
}