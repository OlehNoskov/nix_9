package level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class StringValid {

    public static void verifyValidString(BufferedReader reader) throws IOException {
        System.out.println("===== Поиск допустимой строки =====");
        System.out.println("Введите строку:");
        if (isValidBrackets(reader.readLine())) {
            System.out.println("Данная строка успешно прошла проверку!" + "\n");
        } else {
            System.out.println("Данная строка не допустима!" + "\n");
        }
        ShowSecondLevel.showTasksSecondLevel(reader);
    }

    private static boolean isValidBrackets(String input) throws IOException {

        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');
        Deque<Character> stack = new LinkedList<>();
        for (char c : input.toCharArray()) {
            if (brackets.containsValue(c)) {
                // одна из открывающих скобок
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                // одна из закрывающих скобок
                if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                    return false;
                }
            }
        }
// в конце стек должен быть пустым
        return stack.isEmpty();
    }
}


