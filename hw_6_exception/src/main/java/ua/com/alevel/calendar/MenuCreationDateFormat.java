package ua.com.alevel.calendar;

import java.io.BufferedReader;
import java.io.IOException;

public class MenuCreationDateFormat {

    public static void showMenuCreateCalendar(BufferedReader reader) {
        System.out.println("===== Создание обьекта класса Calendar =====");
        showChoiceDateFormat();
        System.out.println("5.Произвольный ввод");
        System.out.println("6.Выйти в Главное Меню");
        System.out.println("0. Выход из приложения");

        System.out.println("Выберите один из предложенных вариантов:");
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        showFirstCalendarFormat(reader);
                        break;
                    case "2":
                        showSecondCalendarFormat(reader);
                        break;
                    case "3":
                        showThirdCalendarFormat(reader);
                        break;
                    case "4":
                        showFourCalendarFormat(reader);
                        break;
                    case "5":
                        showRandomCalendarFormat(reader);
                        break;
                    case "6":
                        MainMenuProgram.run();
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Введите число от 1 до 6 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный выбор!");
        } catch (IOException e) {
            System.out.println("Введены некорректные данные!");
        }
    }

    private static void showFirstCalendarFormat(BufferedReader reader) {
        System.out.println("Введите данные через пробел в формате 10/10/2020:");
        CreationCalendarFormat.createCalendarFirstFormat(reader);
    }

    private static void showSecondCalendarFormat(BufferedReader reader) {
        System.out.println("Введите данные через пробел в формате 1 4 2021:");
        CreationCalendarFormat.createCalendarSecondFormat(reader);
    }

    private static void showThirdCalendarFormat(BufferedReader reader) {
        System.out.println("Введите данные через пробел в формате Март 4 2021:");
        CreationCalendarFormat.createCalendarThirdFormat(reader);
    }

    private static void showFourCalendarFormat(BufferedReader reader) {
        System.out.println("Введите данные через пробел в формате: 09 Апрель 2020 23:45 ");
        CreationCalendarFormat.createCalendarFourFormat(reader);
    }

    private static void showRandomCalendarFormat(BufferedReader reader) {
        System.out.println("Введите данные:");
        CreationCalendarFormat.inputDataCalendarRandomFormat(reader);
    }

    public static void showChoiceDateFormat() {
        System.out.println("1.dd/mm/yyyy");
        System.out.println("2.m/d/yyyy");
        System.out.println("3.mmm-d-yyyy");
        System.out.println("4.dd-mmm-yyyy 00:00");
    }
}