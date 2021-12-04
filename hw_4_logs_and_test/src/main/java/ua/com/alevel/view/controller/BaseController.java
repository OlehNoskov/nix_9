package ua.com.alevel.view.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseController {
    public static void run() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String position = "0";
            menuNavigation();
            try {
                while (position != null) {
                    position = reader.readLine();
                    if (!position.equals("0")) {
                        showFirstMenu(position, reader);
                    }
                    if (position.equals("0")) {
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                System.out.println("problem: = " + e.getMessage());
            }
        }
    }

    private static void menuNavigation() {
        System.out.println("========== МЕНЮ ==========" + "\n");
        System.out.println("1. Если хотите начать работу с автором нажмите 1");
        System.out.println("2. Если хотите начать работу с книгами нажмите 2");
        System.out.println("3. Для выхода из программы нажмите 0");
    }

    private static void showFirstMenu(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                new AuthorController().run();
                break;
            case "2":
                new BookController().run();
                break;
        }
        menuNavigation();
    }
}