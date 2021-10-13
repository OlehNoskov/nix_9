package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class SecondTask {

    private static TreeMap<Character, Integer> treeMap = new TreeMap<>();

    public static void countCharAndShowResult() {
        System.out.println("Введите строку:");

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String line = bufferedReader.readLine();
            char[] chars = line.toCharArray();
            int count = 1;

            for (int i = 0; i < chars.length; i++) {

                if (Character.isLetter(chars[i])) {

                    if (!treeMap.containsKey(chars[i])) {
                        treeMap.put(chars[i], count);
                    } else {
                        treeMap.put(chars[i], treeMap.get(chars[i]) + 1);
                    }
                }
            }

            for (Map.Entry treemap : treeMap.entrySet()) {
                System.out.println(treemap.getKey() + "-" + treemap.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
