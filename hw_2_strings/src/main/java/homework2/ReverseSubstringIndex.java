package homework2;

import ua.com.alevel.StringReverseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseSubstringIndex {

    public static void showReverseSubstringIndex(BufferedReader reader) throws IOException {
        String line;
        int firstIndex;
        int lastIndex;

        try {
            System.out.println("===== Реверс подстроки в строке по заданным индексам =====");
            System.out.print("Введите строку:");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            line = bufferedReader.readLine();
            System.out.print("Введите  1-ый индекс подстроки:");
            firstIndex = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Введите  2-ый индекс подстроки:");
            lastIndex = Integer.parseInt(bufferedReader.readLine());

            System.out.print("Ваша новая строка:");
            System.out.println(StringReverseUtil.reverse(line, firstIndex, lastIndex));
        } catch (NumberFormatException e) {
            System.out.println("Введен некорректный индекс начала или конца подстроки!");
        }
    }
}
