package ua.com.alevel;

public class test {
    public static void main(String[] args) {

        Number[] numbers = new Number[]{2, 4, 5, 6, 10};
        MathSetUtil mathSetUtil = new MathSetUtil(numbers);
        mathSetUtil.add(20);
        System.out.println(mathSetUtil.toString());
    }

}
