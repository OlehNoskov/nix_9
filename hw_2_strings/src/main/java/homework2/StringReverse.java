package homework2;

import ua.com.alevel.StringReverseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReverse {

    public static void showStringReverse(BufferedReader reader) throws IOException {
        String line;
        String booleanStatus;
        System.out.println("===== Обычный реверс строки =====");
        System.out.print("Введите строку:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        line = bufferedReader.readLine();
        System.out.println("Введите 2-ой параметр:");
        System.out.println("true - ревес символов в словах");
        System.out.print("false - обычный реверс всех символов в строке:");
        booleanStatus = bufferedReader.readLine();
        boolean argument = Boolean.parseBoolean(booleanStatus);

        if (booleanStatus.equals("true") || booleanStatus.equals("false")) {
            System.out.print("Ваша строка:");
            System.out.println(StringReverseUtil.reverse(line, argument));
        } else {
            System.out.println("Некорректно введено булево значение!");
        }
    }
}
