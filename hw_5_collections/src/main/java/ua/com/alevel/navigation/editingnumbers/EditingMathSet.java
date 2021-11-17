package ua.com.alevel.navigation.editingnumbers;

import ua.com.alevel.navigation.sortednumbers.SortedNumbers;
import ua.com.alevel.navigation.creationmathset.MenuCreateMathSet;
import ua.com.alevel.navigation.gettingnumbers.GettingNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditingMathSet {
    public static void showMenuEditMathSet(BufferedReader reader) throws IOException {
        System.out.println("=== Меню работы с MathSet ===" + "\n");
        System.out.println("1.Добавить числа");
        System.out.println("2.Найти общие числа массивов");
        System.out.println("3.Сортировка чисел");
        System.out.println("4.Нахождение чисел по заданным критериям");
        System.out.println("5.Преобразование чисел в строку");
        System.out.println("6.Удаление чисел из массива");
        System.out.println("0. Выход в Меню создания MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        AddingNumbers.showAddingNumbers(reader);
                        break;
                    case "2":
                        IntersectionNumbers.showIntersectionNumbers(reader);
                        break;
                    case "3":
                        SortedNumbers.showSortedNumbers(reader);
                        break;
                    case "4":
                        GettingNumbers.showGettingNumbers(reader);
                        break;
                    case "5":
                        NumbersToArray.showNumbersToArray(reader);
                        break;
                    case "6":
                        DeletedNumbers.showDeletedNumbers(reader);
                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 6 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный выбор!");
        }
    }
}