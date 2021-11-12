package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuCreateMathSet {

    public static void run() {
        showMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu;
        try {
            while ((menu = reader.readLine()) != null) {
                switch (menu) {
                    case "1":
                        CreationMathSetObject.createEmptyMathSet(reader);
                        break;
                    case "2":
                        CreationMathSetObject.setSizeMathSet(reader);
                        break;
                    case "3":
                        CreationMathSetObject.createMathSetArrayNumbers(reader);
                        break;
                    case "4":
                        CreationMathSetObject.createMathSetArraysNumbers(reader);
                        break;
                    case "5":
                        CreationMathSetObject.createMathSet(reader);
                        break;
                    case "6":
                        CreationMathSetObject.showList();
                        break;
                    case "7":
                        EditingMathSet.showMenuEditMathSet(reader);
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Введите число от 1 до 7 для запуска программы");
                        System.out.println("Для выхода из программы введите 0");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("===== Home Work Collections =====" + "\n");
        System.out.println("Для дальнейшей работы необходимо создать MathSet");
        System.out.println("Выберите способ иниализации MathSet");
        System.out.println("1. MathSet()");
        System.out.println("2. MathSet(int capacity)");
        System.out.println("3. MathSet(Number[] numbers)");
        System.out.println("4. MathSet(Number[] ... numbers)");
        System.out.println("5. MathSet(MathSet mathSet)");
        System.out.println("6. MathSet(MathSet ... mathSet)");
        System.out.println("7. Меню добавлений изменений в MathSet");
        System.out.println("0. Выход из программы.");
    }
}