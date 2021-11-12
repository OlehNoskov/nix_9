package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortedNumbers {
    public static void showSortedNumbers(BufferedReader reader) {
        System.out.println("=== Сортировка MathSet ===" + "\n");
        System.out.println("1.Сортировка MathSet на убывание");
        System.out.println("2.Сортировка MathSet на возростание");
        System.out.println("3.Сортировка MathSet на убывание по индексу и до конца массива!");
        System.out.println("4.Сортировка MathSet на возростанию по индексу и до конца массива!");
        System.out.println("5.Сортировка MathSet на убывание от № и до №.");
        System.out.println("6.Сортировка MathSet на возростанию от № и до №.");
        System.out.println("0. Выход в Меню редактирования MathSet.");
        System.out.println("Выберите один из предложенных вариантов:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choiceMenu;
        try {
            while ((choiceMenu = reader.readLine()) != null) {
                switch (choiceMenu) {
                    case "1":
                        showSortDesc(reader);
                        break;
                    case "2":
                        showSortAsc(reader);
                        break;
                    case "3":
                        showSortDescByIndex(reader);
                        break;
                    case "4":
                        showSortAscByIndex(reader);
                        break;
                    case "5":
                        showSortDescByTwoIndex(reader);
                        break;
                    case "6":
                        showSortAscByTwoIndex(reader);
                        break;
                    case "0":
                        MenuCreateMathSet.run();
                        break;
                    default:
                        System.out.println("Введите число от 1 до 6 для запуска задания");
                        System.out.println("Для выхода в меню введите 0");
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор!");
        }
        System.out.println("Выберите один из предложенных вариантов:");
    }

    private static void showSortDesc(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showSortedNumbers(reader);
                } else {
                    CreationMathSetObject.listMathSet.get(choice).sortDesc();
                    System.out.println("Успешно отсортировано по убыванию!");
                    CreationMathSetObject.listMathSet.get(choice).toString();
                    EditingMathSet.showMenuEditMathSet(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void showSortAsc(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showSortedNumbers(reader);
                } else {
                    CreationMathSetObject.listMathSet.get(choice).sortAsc();
                    System.out.println("Успешно отсортировано по возростанию!");
                    CreationMathSetObject.listMathSet.get(choice).toString();
                    EditingMathSet.showMenuEditMathSet(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void showSortDescByIndex(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showSortedNumbers(reader);
                } else {
                    System.out.println("Введите индекс для сортировки:");
                    int index = Integer.parseInt(reader.readLine());
                    if (index > 0 && index < CreationMathSetObject.listMathSet.get(choice).size()) {
                        CreationMathSetObject.listMathSet.get(choice).sortDesc(index);
                        System.out.println("Успешно отсортировано по убыванию!");
                        CreationMathSetObject.listMathSet.get(choice).toString();
                        EditingMathSet.showMenuEditMathSet(reader);
                    } else {
                        System.out.println("Введены некорректные данные");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void showSortAscByIndex(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showSortedNumbers(reader);
                } else {
                    System.out.println("Введите индекс для сортировки:");
                    int index = Integer.parseInt(reader.readLine());
                    if (index > 0 && index < CreationMathSetObject.listMathSet.get(choice).size()) {
                        CreationMathSetObject.listMathSet.get(choice).sortAsc(index);
                        System.out.println("Успешно отсортировано по возростанию!!");
                        CreationMathSetObject.listMathSet.get(choice).toString();
                        EditingMathSet.showMenuEditMathSet(reader);
                    } else {
                        System.out.println("Введены некорректные данные");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void showSortDescByTwoIndex(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showSortedNumbers(reader);
                } else {
                    System.out.println("Введите  1-ый индекс для сортировки:");
                    int index1 = Integer.parseInt(reader.readLine());
                    System.out.println("Введите  2-ой индекс для сортировки:");
                    CreationMathSetObject.listMathSet.get(choice).sortAsc(index1);
                    int index2 = Integer.parseInt(reader.readLine());
                    if (index1 < index2) {
                        CreationMathSetObject.listMathSet.get(choice).sortDesc(index1, index2);
                        System.out.println("Успешно отсортировано по возростанию!!");
                        CreationMathSetObject.listMathSet.get(choice).toString();
                        EditingMathSet.showMenuEditMathSet(reader);
                    } else {
                        System.out.println("Введены некорректные данные!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }

    private static void showSortAscByTwoIndex(BufferedReader reader) {
        System.out.println("Выберите MathSet от 0 до " + (CreationMathSetObject.listMathSet.size() - 1));
        if (CreationMathSetObject.listMathSet.size() != 0) {
            try {
                int choice = Integer.parseInt(reader.readLine());
                if (choice < 0 || choice > CreationMathSetObject.listMathSet.size()) {
                    System.out.println("Введены некорректные данные");
                    showSortedNumbers(reader);
                } else {
                    System.out.println("Введите  1-ый индекс для сортировки:");
                    int index1 = Integer.parseInt(reader.readLine());
                    System.out.println("Введите  2-ой индекс для сортировки:");
                    CreationMathSetObject.listMathSet.get(choice).sortAsc(index1);
                    int index2 = Integer.parseInt(reader.readLine());
                    if (index1 < index2) {
                        CreationMathSetObject.listMathSet.get(choice).sortAsc(index1, index2);
                        System.out.println("Успешно отсортировано по возростанию!!");
                        CreationMathSetObject.listMathSet.get(choice).toString();
                        EditingMathSet.showMenuEditMathSet(reader);
                    } else {
                        System.out.println("Введены некорректные данные!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные");
            }
        } else
            System.out.println("На данный момент нет добавленных MathSet");
        MenuCreateMathSet.run();
    }
}