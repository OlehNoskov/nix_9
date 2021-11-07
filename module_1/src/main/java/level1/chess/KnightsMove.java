package level1.chess;

public class KnightsMove {

    public static void isMovePossible(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 2)
            System.out.println("Данный ход конём возможен!"+"\n");
        else if (Math.abs(x2 - x1) == 2 && Math.abs(y2 - y1) == 1)
            System.out.println("Данный ход конём возможен!"+"\n");
        else
            System.out.println("Данный ход конём невозможно осуществить!"+"\n");
    }
}


