package first_level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AreaTriangle {
    private static double firstSide;
    private static double secondSide;
    private static double thirdSide;

    public static void findAreaTriangle(BufferedReader reader) throws IOException {
        try {
            System.out.println("===== Поиск площади треугольника =====");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Введите 1-ую сторону треугольника:  ");
            firstSide = Double.parseDouble(bufferedReader.readLine());

            System.out.print("Введите 2-ую сторону треугольника:  ");
            secondSide = Double.parseDouble(bufferedReader.readLine());

            System.out.print("Введите 3-ю сторону треугольника:  ");
            thirdSide = Double.parseDouble(bufferedReader.readLine());

            System.out.print("Площадь треугольника: " + getAreaTriangle(firstSide, secondSide, thirdSide));
            System.out.println();
            ShowFirstLevel.showTasksFirstLevel(reader);

        } catch (NumberFormatException e) {
            System.out.println("Введена некорректная длина стороны треугольника!");
        }
    }

    private static double getAreaTriangle(double firstSide, double secondSide, double thirdSide) {
        double halfPerimeter = (firstSide + secondSide + thirdSide) / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - firstSide)
                * (halfPerimeter - secondSide)
                * (halfPerimeter - thirdSide));
    }
}
