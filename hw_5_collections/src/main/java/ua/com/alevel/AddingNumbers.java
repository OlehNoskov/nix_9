package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddingNumbers {
    public static void showAddingNumbers(BufferedReader reader) {
        System.out.println("=== Добавление чисел ===" + "\n");
        System.out.println("1.Добавить число");
        System.out.println("2.Добавить несколько чисел");
        System.out.println("3.Обьеденить 2 MathSet в 1!");
        System.out.println("4.Обьединение нескольких MathSet");
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

                        break;
                    case "3":

                        break;
                    case "4":

                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 4 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void addingNumber(BufferedReader reader) {
        System.out.println("Выберите MathSet для добавления числа от 0 до " + (CreationMathSetObject.listMathSet.size()-1));
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
                }
            } catch (IOException e) {
                e.printStackTrace();
                AddingNumbers.addingNumber(reader);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
                AddingNumbers.addingNumber(reader);
            }
        } else
            System.out.println("На данныый момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void addingNumbers(BufferedReader reader) {
        System.out.println("Выберите MathSet для добавления чисел от 0 до " + (CreationMathSetObject.listMathSet.size()-1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    AddingNumbers.addingNumber(reader);
                } else {
                    System.out.println("== Добавить числа ==");
                    System.out.println("Введите числоа через пробел");
                    String line = reader.readLine();
                    if (CreationMathSetObject.isValidString(line)) {
                        String[] numbers = line.trim().split(" ");
                        for(String number: numbers){
                            double number1 = Double.parseDouble(number);
                        CreationMathSetObject.listMathSet.get(choice).add(number1);
                        System.out.println("Число " + number + " добавлено!");
                        }
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
            System.out.println("На данныый момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }
}