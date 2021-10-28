package second_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinaryTree {

    public static class Node {
        private final int value;
        private Node left;
        private Node right;

        Node(int x) {
            this.value = x;
            left = null;
            right = null;
        }
    }

    public static int deepest(Node root) {
        if (root != null)
            return 1 + Math.max(deepest(root.left), deepest(root.right));
        else
            return 0;
    }

    public static void startSearchDeepest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu = "";
        Node rootNode;
        do {
            try {
                System.out.println("===== Поиск глубины бинарного дерева =====");
                System.out.println("Введите корневое значение: ");
                rootNode = new Node(Integer.parseInt(reader.readLine()));
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Введены некорректные данные!");
                System.out.println("Данная программа принимает цифры!");
                startSearchDeepest();
            }
        } while (true);
        do {
            System.out.println("1. Введите новое значение:");
            System.out.println("2. Найти максимальную глубину:");
            System.out.println("3. Выход в меню 2 уровня.");
            menu = reader.readLine();
            switch (menu) {
                case "1": {
                    do {
                        try {
                            System.out.print("Введите новое значение: ");
                            insert(rootNode, Integer.parseInt(reader.readLine()));
                            System.out.println();
                            break;
                        } catch (NumberFormatException ex) {
                            System.out.println("Введены некорректные данные!");
                            System.out.println("Данная программа принимает цифры!");
                        }
                    } while (true);
                    break;
                }
                case "2": {
                    System.out.println("Максимальная глубина: " + deepest(rootNode)+"\n");
                    ShowSecondLevel.showTasksSecondLevel(reader);
                    break;
                }
                case "3": {
                    ShowSecondLevel.showTasksSecondLevel(reader);
                    break;
                }
            }
        } while (true);
    }

    public static void insert(Node node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new Node(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                node.right = new Node(value);
            }
        }
    }
}
