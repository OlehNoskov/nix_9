package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            if (isValidString(inputString)) {
            String[] numbersForLine = inputString.trim().split(" ");
            Number[] arrayNumbers = new Number[numbersForLine.length];

            for (int i = 0; i < numbersForLine.length; i++) {
                arrayNumbers[i] = Double.parseDouble(numbersForLine[i]);
            }

            Number[] result = new Number[arrayNumbers.length];
            for (int i = 0; i < arrayNumbers.length; i++) {
                for (int j = 1; j < arrayNumbers.length; j++) {
                    if(Objects.equals(arrayNumbers[i], arrayNumbers[j])){
                        result[i] = arrayNumbers[i];
                    }
                }
            }
            MathSetUtil mathSet = new MathSetUtil(result);
            listMathSet.add(mathSet);
            System.out.println("Массив успешно создан!");
            System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSet) + "\n");
            EditingMathSet.showMenuEditMathSet(reader);
            } else
                System.out.println("Введены некорректные данные!" + "\n");
            CreationMathSetObject.createMathSetArrayNumbers(reader);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n");
            MenuCreateMathSet.run();
        }
    }

//    public static void createMathSetArraysNumbers(BufferedReader reader) {
//        System.out.println("=== Создание MathSet с помощью массивов ===");
//        if (arrayNumbers.size() == 0) {
//            System.out.println("На данный момент нет созданных массивов!");
//        } else {
//            System.out.println("Доступные номера масивов от 0 до " + (arrayNumbers.size()-1) + ":");
//            System.out.print("Введите номер массива Numbers:");
//            try {
//                int choiceArray = Integer.parseInt(reader.readLine());
//                List<Number[]> arrayForCreateMathSet = new ArrayList<>();
//                while (reader.ready()) {
//                    arrayForCreateMathSet.add(arrayNumbers.get(choiceArray));
//                }
//                Number[] arrayMathSet = (Number[]) arrayForCreateMathSet.toArray();
//                MathSetUtil mathSet = new MathSetUtil(arrayMathSet);
//                listMathSet.add(mathSet);
//                System.out.println("MathSet успешно создан!");
//                System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSet));
//            } catch (IOException e) {
//                e.printStackTrace();
//                MenuCreateMathSet.run();
//            } catch (NumberFormatException e) {
//                System.out.println("Введены некорректные данные!"+ "\n");
//                MenuCreateMathSet.run();
//            }
//        }
//    }
//    public static void createMathSet(BufferedReader reader){
//        System.out.println("=== Создание MathSet с помощью MathSet ===");
//        if (listMathSet.size() == 0) {
//            System.out.println("На данный момент нет созданных MathSet!");
//        } else {
//            System.out.println("Доступные номера MathSet от 0 до " + (arrayNumbers.size()-1) + ":");
//            System.out.print("Введите номер MathSet:");
//            try {
//                int choiceMathSet = Integer.parseInt(reader.readLine());
//                for(int i=0; i<listMathSet.size(); i++){
//                    if(choiceMathSet == i){
//                        MathSetUtil mathSetUtil = new MathSetUtil(listMathSet.get(i));
//                        listMathSet.add(mathSetUtil);
//                        System.out.println("MathSet успешно создан!");
//                        System.out.println("Номер данного MathSet " + listMathSet.indexOf(mathSetUtil)+ "\n");
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                MenuCreateMathSet.run();
//            } catch (NumberFormatException e) {
//                System.out.println("Введены некорректные данные!"+ "\n");
//                MenuCreateMathSet.run();
//            }
//        }
//    }

    public static boolean isValidString(String inputString) {
        return inputString.matches("^(-?\\d+(\\.\\d+)?)+$");
    }

    public static void showList() {
        for (MathSetUtil mathSetUtil : listMathSet) {
            System.out.println(mathSetUtil);
        }
    }
}


//    private static Number[] checkUniqueNumbers(BufferedReader reader) throws IOException {
//        Number[] numbers = new Number[reader.readLine().length()];
//        int count = 0;
//        try {
//            String inputString = reader.readLine();
//            if (inputString.isEmpty()) {
//                String[] uniqNumbers = inputString.trim().split(" ");
//                numbers = new Number[uniqNumbers.length];
//                for (String number : uniqNumbers) {
//                    char[] digit = number.toCharArray();
//
//                    for (int charNumber : digit) {
//                        if (Character.isDigit(charNumber)) {
//                            numbers[count] = charNumber;
//                            count++;
//                        }
//                    }
//                }
//            } else {
//                System.out.println("Вы не ввели значения!");
//                SetNumbers.createArrayNumbers(reader);
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return numbers;
//    }