package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreationMathSetObject {

    public static List<MathSetUtil> listMathSet = new ArrayList<>();

    public static void createEmptyMathSet(BufferedReader reader) throws IOException {
        MathSetUtil mathSet = new MathSetUtil();
        listMathSet.add(mathSet);
        System.out.println("Пустой массив MathSet успешно создан!");
        System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSet) + "\n");
        EditingMathSet.showMenuEditMathSet(reader);
    }

    public static void setSizeMathSet(BufferedReader reader) {
        System.out.println("=== Установка размера массива ===");
        System.out.print("Введите размер массива:");
        try {
            int sizeMathSet = Integer.parseInt(reader.readLine());
            if (sizeMathSet > 0) {
                MathSetUtil mathSet = new MathSetUtil(sizeMathSet);
                listMathSet.add(mathSet);
                System.out.println("MathSet с размером " + sizeMathSet + " успешно создан!" + "\n");
                System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSet));
                EditingMathSet.showMenuEditMathSet(reader);
            } else {
                CreationMathSetObject.setSizeMathSet(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
            CreationMathSetObject.setSizeMathSet(reader);
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели некорректные значения размера массива!" + "\n");
            CreationMathSetObject.setSizeMathSet(reader);
        }
    }

    public static void createMathSetArrayNumbers(BufferedReader reader) throws IOException {
        System.out.println("=== Создание массива Numbers ===");
        System.out.print("Введите числа через пробел:");
        try {
            String inputString = reader.readLine();
            String[] numbersForLine = inputString.trim().split(" ");
            Number[] arrayNumbers = new Number[numbersForLine.length];

            for (int i = 0; i < numbersForLine.length; i++) {
                arrayNumbers[i] = Double.parseDouble(numbersForLine[i]);
            }

            Number[] result = new Number[arrayNumbers.length];
            for (int i = 0; i < arrayNumbers.length; i++) {
                result[i] = arrayNumbers[i];
            }
            MathSetUtil mathSet = new MathSetUtil(result);
            listMathSet.add(mathSet);
            System.out.println("Массив успешно создан!");
            System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSet) + "\n");
            EditingMathSet.showMenuEditMathSet(reader);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n");
            MenuCreateMathSet.run();
        } catch (NumberFormatException e) {
            System.out.println("Некорректные данные");
            MenuCreateMathSet.run();
        }
    }

    public static void createMathSetArraysNumbers(BufferedReader reader) {
        System.out.println("=== Создание MathSet с помощью массивов ===");
        if (listMathSet.size() == 0) {
            System.out.println("На данный момент нет созданных массивов!");
        } else {
            System.out.println("Доступные номера масивов от 0 до " + (listMathSet.size() - 1) + ":");
            System.out.print("Введите номер массивов через пробел Numbers:");
            try {
                String choice = reader.readLine();
                String[] choiceArray = choice.trim().split(" ");
                int[] choiceIndexList = new int[choiceArray.length];
                for (int i = 0; i < choiceArray.length; i++) {
                    choiceIndexList[i] = Integer.parseInt(choiceArray[i]);
                }
                MathSetUtil mathSet = new MathSetUtil();
                System.out.println("MathSet успешно создан!");
                System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSet));
                MenuCreateMathSet.run();
            } catch (IOException e) {
                e.printStackTrace();
                MenuCreateMathSet.run();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные!" + "\n");
                MenuCreateMathSet.run();
            }
        }
    }

    public static void createMathSet(BufferedReader reader) {
        System.out.println("=== Создание MathSet с помощью MathSet ===");
        if (listMathSet.size() == 0) {
            System.out.println("На данный момент нет созданных MathSet!");
        } else {
            System.out.println("Доступные номера MathSet от 0 до " + (listMathSet.size() - 1) + ":");
            System.out.print("Введите номер MathSet:");
            try {
                int choiceMathSet = Integer.parseInt(reader.readLine());
                for (int i = 0; i < listMathSet.size(); i++) {
                    if (choiceMathSet == i) {
                        MathSetUtil mathSetUtil = new MathSetUtil(listMathSet.get(i));
                        listMathSet.add(mathSetUtil);
                        System.out.println("MathSet успешно создан!");
                        System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSetUtil) + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                MenuCreateMathSet.run();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные!" + "\n");
                MenuCreateMathSet.run();
            }
        }
    }

    public static void showList() {
        for (MathSetUtil mathSetUtil : listMathSet) {
            System.out.println(mathSetUtil);
        }
    }
}