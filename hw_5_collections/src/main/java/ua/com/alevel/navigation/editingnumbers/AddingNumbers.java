package ua.com.alevel.navigation.editingnumbers;

import ua.com.alevel.MathSetUtil;
import ua.com.alevel.navigation.creationmathset.CreationMathSetObject;
import ua.com.alevel.navigation.creationmathset.MenuCreateMathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddingNumbers {
    public static void showAddingNumbers(BufferedReader reader) {
        System.out.println("=== Добавление чисел ===" + "\n");
        System.out.println("1.Добавить число");
        System.out.println("2.Добавить несколько чисел");
        System.out.println("3.Обьединить 2 MathSet в 1!");
        System.out.println("0. Выход в Меню редактирования MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        addingNumber(reader);
                        break;
                    case "2":
                        addingNumbers(reader);
                        break;
                    case "3":
                        joinMathSet(reader);
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

    private static void addingNumber(BufferedReader reader) {
        System.out.println("Выберите MathSet для добавления числа от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    AddingNumbers.addingNumber(reader);
                } else {
                    System.out.println("== Добавить число ==");
                    double add = Double.parseDouble(reader.readLine());
                    CreationMathSetObject.listMathSet.get(choice).add(add);
                    System.out.println("Число " + add + " добавлено!");
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                AddingNumbers.addingNumber(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                AddingNumbers.addingNumber(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void addingNumbers(BufferedReader reader) {
        System.out.println("Выберите MathSet для добавления чисел от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    AddingNumbers.addingNumber(reader);
                } else {
                    System.out.println("== Добавить числа ==");
                    System.out.println("Введите числа через пробел");
                    String line = reader.readLine();
                    String[] numbers = line.trim().split(" ");
                    for (String number : numbers) {
                        double number1 = Double.parseDouble(number);
                        CreationMathSetObject.listMathSet.get(choice).add(number1);
                        System.out.println("Число " + number + " добавлено!");
                    }
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                AddingNumbers.addingNumber(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                AddingNumbers.addingNumber(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void joinMathSet(BufferedReader reader) {
        System.out.println("Выберите 1-ый MathSet для обьединения  от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                } else {
                    MathSetUtil mathSet = new MathSetUtil(CreationMathSetObject.listMathSet.get(choice));
                    System.out.println("== Введите номер 2-го MathSet ==");
                    int secondChoice = Integer.parseInt(reader.readLine());
                    if ( secondChoice< 0 || secondChoice > CreationMathSetObject.listMathSet.size()) {
                        System.out.println("Введены некорректные данные");
                    }else {
                        CreationMathSetObject.listMathSet.get(choice).join(CreationMathSetObject.listMathSet.get(secondChoice));
                        System.out.println("MathSet успешно обьединен!");
                        System.out.println("Номер данного MathSet " + CreationMathSetObject.listMathSet.get(choice));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                AddingNumbers.addingNumber(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                AddingNumbers.addingNumber(reader);
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }
}