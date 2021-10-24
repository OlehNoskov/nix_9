package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuProgram {

    public static void run() {
        showMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu;
        try {
            while ((menu = reader.readLine()) != null) {
                switch (menu) {
                    case "1":
                        StringReverse.showStringReverse(reader);
                        break;
                    case "2":
                        ReverseSubstring.showReverseSubstring(reader);
                        break;
                    case "3":
                        ReverseSubstringIndex.showReverseSubstringIndex(reader);
                        break;
                    case "4":
                        System.exit(4);
                        break;
                    default:
                        System.out.println("Введите число от 1 до 3 для запуска программы");
                        System.out.println("Для выхода из программы введите 4");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Данная программа выполняет 3 действия:");
        System.out.println("1.Обычный реверс строки");
        System.out.println("2.Реверс по указанной подстроке в строке");
        System.out.println("3.Реверс подстроки в строке по заданным индексам.");
        System.out.println("4.Выход из программы");
        System.out.println();
        System.out.println("Выберите один из предложенных вариантов:");
    }
}
