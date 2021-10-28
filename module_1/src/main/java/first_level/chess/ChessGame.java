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
            System.out.println("Введите столбец для установки коня по вертикали:");
            initialPositionСolumn = reader.readLine().toUpperCase();

            System.out.println("Введите номер строки для установки коня по горизонтали:");
            initialPositionLine = Integer.parseInt(reader.readLine());
            if (initialPositionLine > 8 || initialPositionLine < 0) {
                System.out.println("Вы вышли за пределы шахматной доски!"+"\n");
                ShowFirstLevel.showTasksFirstLevel(reader);
            }

            System.out.println("Ваш конь установлен на позиции: " +
                    initialPositionСolumn + initialPositionLine);

            System.out.println("=== Ход конём ===");
            System.out.println("Введите столбец для хода коня по вертикали:");
            movePositionСolumn = reader.readLine().toUpperCase();

            System.out.println("Введите номер строки для хода коня по горизонтали:");
            movePositionLine = Integer.parseInt(reader.readLine());

            if (movePositionLine > 8 || movePositionLine < 0) {
                System.out.println("Вы вышли за пределы шахматной доски!"+"\n");
                ShowFirstLevel.showTasksFirstLevel(reader);
            }

            KnightsMove.isMovePossible(ChessBoard.getNumberColumnChessBoard(initialPositionСolumn), initialPositionLine,
                    ChessBoard.getNumberColumnChessBoard(movePositionСolumn), movePositionLine);

            ShowFirstLevel.showTasksFirstLevel(reader);
        } catch (NumberFormatException e) {
            System.out.println("Введены некорректные данные!"+"\n");
            ShowFirstLevel.showTasksFirstLevel(reader);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

