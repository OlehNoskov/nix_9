package homework;

import java.io.IOException;
import java.util.Scanner;

public class ThirdTask {
    public static void lessonsEnd() throws IOException {
        int startLesson = 540;//время начала уроков в минутах.
        int smallBreak = 5;
        int bigBreak = 15;
        int hours;
        int minutes;

        System.out.println("Введите номер урока от 1 до 10: ");
        try (Scanner scanner = new Scanner(System.in)) {
            int numberLesson = scanner.nextInt();
            int countBreak = numberLesson - 1;

            hours = (startLesson + numberLesson * 45 + countBreak / 2 * 15 + (countBreak - countBreak / 2) * 5) / 60;
            minutes = (startLesson + numberLesson * 45 + countBreak / 2 * 5 + (countBreak - countBreak / 2) * 15) % 60;

            System.out.println("Время окончание "+numberLesson+" урока "+ hours + ":" + minutes);
        }
    }
}
