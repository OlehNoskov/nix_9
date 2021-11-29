package level1;

import java.io.BufferedReader;
import java.io.IOException;

public class AreaTriangle {

    private static int areaTriangle;
    private static int count = 0;
    private static int[] coordinatesX = new int[3];
    private static int[] coordinatesY = new int[3];

    public static void findAreaTriangle(BufferedReader reader) throws IOException {
        System.out.println("===== Поиск площади треугольника =====");

        for (int i = 0; i < 3; i++) {
            do {
                try {
                    System.out.print("Введите координаты X вершины №" + (i + 1) + ": ");
                    coordinatesX[i] = Integer.parseInt(reader.readLine());

                    System.out.print("Введите координаты Y вершины №" + (i + 1) + ": ");
                    coordinatesY[i] = Integer.parseInt(reader.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Введены некорректные координаты треугольника!" + "\n");
                    AreaTriangle.findAreaTriangle(reader);
                }
            } while (count < 3);
            {
                count++;
            }
        }
        areaTriangle = ((coordinatesX[1] - (coordinatesX[0]))
                * (coordinatesY[2] - (coordinatesY[0]))
                - (coordinatesX[2] - (coordinatesX[0]))
                * (coordinatesY[1] - (coordinatesY[0]))) / -2;

        System.out.println("Площадь треyгольника: " + areaTriangle + "\n");
        ShowFirstLevel.showTasksFirstLevel(reader);
    }
}