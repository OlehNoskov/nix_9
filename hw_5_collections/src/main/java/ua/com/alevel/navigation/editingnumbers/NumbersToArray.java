package ua.com.alevel.navigation.editingnumbers;

import ua.com.alevel.navigation.creationmathset.CreationMathSetObject;
import ua.com.alevel.navigation.creationmathset.MenuCreateMathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumbersToArray {
    public static void showNumbersToArray(BufferedReader reader) {
        System.out.println("=== Преобразование MathSet toArray ===" + "\n");
        System.out.println("1.Преобразование MathSet toArray ");
        System.out.println("2.Преобразование MathSet toArray от № индекс до № индекс");
        System.out.println("0. Выход в Меню редактирования MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                    showMathSetToArray(reader);
                        break;
                    case "2":
                    showMathSetToArrayByIndex(reader);
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
    private static void showMathSetToArray(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showNumbersToArray(reader);
                } else {
                    CreationMathSetObject.listMathSet.get(choice).toArray();
                    System.out.println("Преобоазование MathSet toArray выполнено успешно!!!");
                    System.out.println(CreationMathSetObject.listMathSet.get(choice).toString());
                    EditingMathSet.showMenuEditMathSet(reader);
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

    private static void showMathSetToArrayByIndex(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showNumbersToArray(reader);
                } else {
                    System.out.println("Введите  1-ый индекс от 0 до "+CreationMathSetObject.listMathSet.get(choice)+":");
                    int index1 = Integer.parseInt(reader.readLine());
                    System.out.println("Введите  2-ой индекс от 0 до " +CreationMathSetObject.listMathSet.get(choice)+":");
                    int index2 = Integer.parseInt(reader.readLine());
                    if (index1 < index2) {
                        CreationMathSetObject.listMathSet.get(choice).toArray(index1, index2);
                        System.out.println("Успешно!!!");
                        System.out.println(CreationMathSetObject.listMathSet.get(choice).toString());
                        EditingMathSet.showMenuEditMathSet(reader);
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
}