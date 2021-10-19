package ua.com.alevel;

public class StringReverseUtil {
    private StringReverseUtil() {
    }

    private static String fullStringReverse(String src) {
        char[] string = src.toCharArray();
        char[] reverseString = new char[string.length];
        int count = 0;

        for (int i = string.length - 1; i >= 0; i--) {
            reverseString[count] = string[i];
            count++;
        }
        return String.valueOf(reverseString);
    }

    public static String reverse(String src, boolean full) {
        if (full == true) {
            String[] words = src.trim().split(" ");
            String reverse = "";

            for (String word : words) {
                char[] wordToChar = word.toCharArray();
                char[] wordCharReverse = new char[wordToChar.length];
                int count = wordCharReverse.length - 1;

                for (int i = 0; i < wordToChar.length; i++) {
                    wordCharReverse[count] = wordToChar[i];
                    count--;
                }
                reverse += String.valueOf(wordCharReverse) + " ";
            }
            return reverse.trim();
        } else
            return fullStringReverse(src);
    }

    public static String reverse(String src, String dest) {
        String stringReverse;
        int index = src.indexOf(dest);
        if (index == -1) {
            return "Введены некорректные данные. Данная подстрока не является частью строки!";
        }
        stringReverse = src.substring(0, index) + fullStringReverse(dest) + src.substring(index + dest.length());
        return stringReverse;
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        String stringReverse = "";

        if (firstIndex >= 0 && lastIndex > firstIndex && lastIndex <= src.length()) {
            String substring = src.substring(firstIndex, lastIndex + 1);
            stringReverse = src.substring(0, firstIndex) + reverse(substring, true) + src.substring(lastIndex + 1);
        } else
            System.out.println("Введены некорректные данные!");

        return stringReverse;
    }
}
