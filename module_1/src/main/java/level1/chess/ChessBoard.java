package level1.chess;

public class ChessBoard {

    private static int chessBoard[][] = new int[8][8];

    public static void createChessBoard() {
        int count = 1;
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++)
                if ((i + j) % 2 == 0)
                    System.out.print("0 ");
                else
                    System.out.print("# ");
            System.out.println(" " + count++);
        }
        System.out.println("A B C D E F G H");
    }

    public static int getNumberColumnChessBoard(String numberColumn) {
        int numberColumnChessBoard = 0;
        switch (numberColumn) {
            case "A":
                numberColumnChessBoard = 1;
                break;
            case "B":
                numberColumnChessBoard = 2;
                break;
            case "C":
                numberColumnChessBoard = 3;
                break;
            case "D":
                numberColumnChessBoard = 4;
                break;
            case "E":
                numberColumnChessBoard = 5;
                break;
            case "F":
                numberColumnChessBoard = 6;
                break;
            case "G":
                numberColumnChessBoard = 7;
                break;
            case "H":
                numberColumnChessBoard = 8;
                break;
            default:
                System.out.println("Некорректно выбран столбец!");
        }
        return numberColumnChessBoard;
    }
}


