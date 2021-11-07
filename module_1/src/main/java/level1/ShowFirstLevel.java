package level1;

import consolemenu.MenuProgram;
import level1.chess.ChessGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowFirstLevel {

    public static void showTasksFirstLevel(BufferedReader reader) throws IOException {
        System.out.println("===== Демонстрация заданий 1-го уровня =====");
        System.out.println("1.Поиск количества уникальных символов");
        System.out.println("2.Ход конём");
        System.out.println("3.Поиск площадь треугольника");
        System.out.println("0.Главное меню");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        UniqNumbers.countDigit(bufferedReader);
                        break;
                    case "2":
                        ChessGame.startMoveKnight(reader);
                        break;
                    case "3":
                        AreaTriangle.findAreaTriangle(bufferedReader);
                        break;
                    case "0":
                        MenuProgram.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 3 для запуска задания");
                        System.out.println("Для выхода в главное меню введите 0");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный выбор!");
        }
    }
}