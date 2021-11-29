package ua.com.alevel.navigation.editingnumbers;

import ua.com.alevel.navigation.creationmathset.CreationMathSetObject;
import ua.com.alevel.navigation.creationmathset.MenuCreateMathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeletedNumbers {
    public static void showDeletedNumbers(BufferedReader reader) {
        System.out.println("=== Вырезать и удалить MathSet ===" + "\n");
        System.out.println("1.Вырезать данные из MathSet");
        System.out.println("2.Очистить всё");
        System.out.println("3.Очистить массив Numbers");
        System.out.println("0. Выход в Меню редактирования MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        showCutNumbersTwoIndex(reader);
                        break;
                    case "2":
                        showClearAll(reader);
                        break;
                    case "3":
                        showClearArrayNumbers(reader);
                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 3 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void showCutNumbersTwoIndex(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showDeletedNumbers(reader);
                } else {
                    System.out.println("Введите  1-ый индекс от 0 до " + CreationMathSetObject.listMathSet.get(choice).size() + ":");
                    int index1 = Integer.parseInt(reader.readLine());
                    System.out.println("Введите  2-ой индекс от " + index1 + " до " + CreationMathSetObject.listMathSet.get(choice).size() + ":");
                    int index2 = Integer.parseInt(reader.readLine());
                    if (index1 < index2) {
                        CreationMathSetObject.listMathSet.get(choice).cut(index1, index2);
                        System.out.println("Успешно вырезано!!!");
                        System.out.println(CreationMathSetObject.listMathSet.get(choice).toString());
                        showDeletedNumbers(reader);
                    } else {
                        System.out.println("Введены некорректные данные!");
                    }
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

    private static void showClearAll(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showDeletedNumbers(reader);
                } else {
                    CreationMathSetObject.listMathSet = null;
                    System.out.println("Список MathSet пуст!");
                    showDeletedNumbers(reader);
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

    private static void showClearArrayNumbers(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showDeletedNumbers(reader);
                } else {
                    CreationMathSetObject.listMathSet.get(choice).clear();
                    System.out.println("Список MathSet пуст!");
                    showDeletedNumbers(reader);
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