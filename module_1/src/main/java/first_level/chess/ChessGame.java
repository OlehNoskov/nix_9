package first_level.chess;

import first_level.ShowFirstLevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class ChessGame {

    public static void startMoveKnight(BufferedReader reader) throws IOException {
        System.out.println("===== Ход коня по шахматной доске =====");
        try {
            ChessBoard.createChessBoard();
            int initialPositionLine, movePositionLine;
            String initialPositionСolumn, movePositionСolumn;

            System.out.println("=== Установка коня ===");
            System.out.println("Введите столбец для установки коня по горизонтали:");
            initialPositionСolumn = reader.readLine().toUpperCase();

            System.out.println("Введите номер строки для установки коня по вертикали:");
            initialPositionLine = Integer.parseInt(reader.readLine());

            System.out.println("Ваш конь установлен на позиции: " +
                    initialPositionСolumn + initialPositionLine);

            System.out.println("=== Ход конём ===");
            System.out.println("Введите столбец для хода коня по горизонтали:");
            movePositionСolumn = reader.readLine().toUpperCase();

            System.out.println("Введите номер строки для хода коня по вертикали:");
            movePositionLine = Integer.parseInt(reader.readLine());

            KnightsMove.isMovePossible(ChessBoard.getNumberColumnChessBoard(initialPositionСolumn), initialPositionLine,
                    ChessBoard.getNumberColumnChessBoard(movePositionСolumn), movePositionLine);
            ShowFirstLevel.showTasksFirstLevel(reader);
        } catch (NumberFormatException e) {
            System.out.println("Введены некорректные данные!");
            ShowFirstLevel.showTasksFirstLevel(reader);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
