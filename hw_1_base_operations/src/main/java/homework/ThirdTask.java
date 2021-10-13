package homework;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThirdTask {
    private int startLesson = 540;//время начала уроков в минутах.
    private int lessonTime = 45;
    private int smallBreak = 5;
    private int bigBreak = 15;
    private int hours;
    private int minutes;

    public void showTimeLessonsEnd(BufferedReader reader) throws IOException {
        System.out.println("Запуск 3-го домашнего задания");
        System.out.println("Введите номер урока от 1 до 10: ");
        try {

            String readLine = reader.readLine();
            int numberLesson = Integer.parseInt(readLine);
            int countBreak = numberLesson - 1;

            if (numberLesson == 0) {
                System.out.println("В данной школе нет нулевого урока! Пусть школьники спят больше)");

            } else if (numberLesson >= 1 && numberLesson <= 10) {

                hours = (startLesson + numberLesson * lessonTime + countBreak / 2 * 15 + (countBreak - countBreak / 2) * 5) / 60;
                minutes = (startLesson + numberLesson * lessonTime + countBreak / 2 * 5 + (countBreak - countBreak / 2) * 15) % 60;

                System.out.println("Время окончание " + numberLesson + " урока " + hours + ":" + minutes);

            } else
                showIncorrectData();
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели некорректные данные!!!");
            showIncorrectData();
        }
    }

    private void showIncorrectData() {
        System.out.println("Нажмите 3 для выбора программы поиска окончания уроков");
        System.out.println("Нажмите кнопку ENTER");
        System.out.println("Введите номер урока от 1 до 10!");
    }
}



