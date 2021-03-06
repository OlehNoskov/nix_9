package ua.com.alevel.navigation.gettingnumbers;

import ua.com.alevel.navigation.editingnumbers.EditingMathSet;
import ua.com.alevel.navigation.creationmathset.CreationMathSetObject;
import ua.com.alevel.navigation.creationmathset.MenuCreateMathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GettingNumbers {
    public static void showGettingNumbers(BufferedReader reader) {
        System.out.println("=== Получение чисел из MathSet ===" + "\n");
        System.out.println("1.Получить число по индексу");
        System.out.println("2.Получение максимального числа из массива");
        System.out.println("3.Получение минимального числа из массива");
        System.out.println("4.Получение среднего значения из массива");
        System.out.println("5.Получение медианы значения из массива");
        System.out.println("0. Выход в Меню редактирования MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        getNumberForIndex(reader);
                        break;
                    case "2":
                        getMAxNumberForMathSet(reader);
                        break;
                    case "3":
                        getMinNumberForMathSet(reader);
                        break;
                    case "4":
                        getAverageNumberForMathSet(reader);
                        break;
                    case "5":
                        getMedianeNumberForMathSet(reader);
                        break;
                    case "6":
                        EditingMathSet.showMenuEditMathSet(reader);
                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 5 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void getNumberForIndex(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1)
                + " для получения числа!");
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    getNumberForIndex(reader);
                } else {
                    System.out.println("Выберите индекс от 0 до " + CreationMathSetObject.listMathSet.get(choice).size() + ":");
                    int index = Integer.parseInt(reader.readLine());
                    System.out.print("Число:");
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).get(index));
                    EditingMathSet.showMenuEditMathSet(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
                GettingNumbers.getNumberForIndex(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                GettingNumbers.getNumberForIndex(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void getMAxNumberForMathSet(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    getMAxNumberForMathSet(reader);
                } else {
                    System.out.println("Максимальное число:");
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).getMax());
                    EditingMathSet.showMenuEditMathSet(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
                GettingNumbers.getNumberForIndex(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                GettingNumbers.getNumberForIndex(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void getMinNumberForMathSet(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    getMinNumberForMathSet(reader);
                } else {
                    System.out.println("Минимальное число:");
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).getMin());
                    EditingMathSet.showMenuEditMathSet(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
                GettingNumbers.getNumberForIndex(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                GettingNumbers.getNumberForIndex(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void getAverageNumberForMathSet(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    getAverageNumberForMathSet(reader);
                } else {
                    System.out.println("Среднее число:");
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).getAverage());
                    EditingMathSet.showMenuEditMathSet(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
                GettingNumbers.getNumberForIndex(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                GettingNumbers.getNumberForIndex(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void getMedianeNumberForMathSet(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    getMedianeNumberForMathSet(reader);
                } else {
                    System.out.println("Число медианы:");
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).getMedian());
                    EditingMathSet.showMenuEditMathSet(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
                GettingNumbers.getNumberForIndex(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                GettingNumbers.getNumberForIndex(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }
}