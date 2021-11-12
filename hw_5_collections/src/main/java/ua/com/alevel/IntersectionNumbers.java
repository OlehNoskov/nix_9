package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntersectionNumbers {
    public static void showIntersectionNumbers(BufferedReader reader) {
        System.out.println("=== Нахождение общих чисел ===" + "\n");
        System.out.println("1.Нахождение общих чисел 2-ух MathSet");
        System.out.println("2.Нахождение общих чисел нескольких MathSet");
        System.out.println("0. Выход в Меню редактирования MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        showIntersection(reader);
                        break;
                    case "2":
                        showIntersectionTwoMethod(reader);
                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 2 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void showIntersection(BufferedReader reader) {
        System.out.println("Выберите 1-ый MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice1 = Integer.parseInt(reader.readLine());
                if (choice1 > 0 && choice1 < CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showIntersectionNumbers(reader);
                    System.out.println("Выберите 2-ый MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
                    int choice2 = Integer.parseInt(reader.readLine());
                    if (choice2 > 0 && choice2 < CreationMathSetObject.listMathSet.size()) {
                        CreationMathSetObject.listMathSet.get(choice1).
                                intersection(CreationMathSetObject.listMathSet.get(choice2));
                        System.out.println("Действие выполнено успешно!");
                    }
                } else {
                    System.out.println("Введены некорректные данные");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void showIntersectionTwoMethod(BufferedReader reader) {
        System.out.println("Выберите 1-ый MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice1 = Integer.parseInt(reader.readLine());
                if (choice1 > 0 && choice1 < CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showIntersectionNumbers(reader);
                    System.out.println("Выберите 2-ый MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
                    int choice2 = Integer.parseInt(reader.readLine());
                    if (choice2 > 0 && choice2 < CreationMathSetObject.listMathSet.size()) {
                        CreationMathSetObject.listMathSet.get(choice1).
                                intersection(CreationMathSetObject.listMathSet.get(choice2));
                        System.out.println("Действие выполнено успешно!");
                    }
                } else {
                    System.out.println("Введены некорректные данные");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }
}