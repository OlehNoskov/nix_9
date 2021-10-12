package homework;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Данная программа выполняет 3 действия:");
        System.out.println("1.Запуск 1-го домашнего задания");
        System.out.println("2.Запуск 2-го домашнего задания");
        System.out.println("3.Запуск 3-го домашнего задания");
        System.out.println("Выберите один из предложенных вариантов:");

        Scanner scanner = new Scanner(System.in);
        int menu = scanner.nextInt();

        switch (menu) {
            case 1:
                FirstTask.sumNumbers();
                break;
            case 2:
                SecondTask.countCharAndShowResult();
                break;
            case 3:
                ThirdTask.lessonsEnd();
                break;
            default:
                System.out.println("Введите число от 1 до 3");
        }
        scanner.close();
    }
}
