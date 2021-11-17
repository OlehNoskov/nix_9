package ua.com.alevel.calendar.editcalendar;

import ua.com.alevel.calendar.MainMenuProgram;

import java.io.BufferedReader;
import java.io.IOException;

public class EditCalendar {

    public static void selectAdditionAndSubtractionDates(BufferedReader reader) throws IOException {
        System.out.println("=== Выберите действие для работы с датой ===");
        System.out.println("1. Прибавить к дате год, дни, часы, минуты, секунды и миллисекунды.");
        System.out.println("2. Вычесть из даты год, дни, часы, минуты, секунды и миллисекунды.");
        System.out.println("0. Выход в Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");
        System.out.println("");
        String menu = reader.readLine();
        switch (menu) {
            case "1":
                AddingTime.selectDate(reader);
                break;
            case "2":
                SubtractionDate.selectDate(reader);
                break;
            case "0":
                MainMenuProgram.run();
                break;
            default:
                System.out.println("Введите число от 1 до 2 для запуска программы");
                System.out.println("Для выхода в Главное Меню введите 0");
        }
    }
}