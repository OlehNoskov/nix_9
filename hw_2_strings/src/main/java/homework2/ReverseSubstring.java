package homework2;

import ua.com.alevel.StringReverseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseSubstring {

    public static void showReverseSubstring(BufferedReader reader) throws IOException {
        String line;
        String substring;
        System.out.println("===== Реверс по указанной подстроке в строке =====");
        System.out.print("Введите строку:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        line = bufferedReader.readLine();
        System.out.print("Введите подстроку:");
        substring = bufferedReader.readLine();

        System.out.print("Ваша новая строка:");
        System.out.println(StringReverseUtil.reverse(line, substring));
    }
}
