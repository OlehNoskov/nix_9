package ua.com.alevel;

public class Test {
    public static void main(String[] args) {
        Number[] numbers = new Number[]{1,2,3,4,5,6};
        MathSetUtil mathSet = new MathSetUtil(numbers);
        Number i = mathSet.getAverage();
        System.out.println(i);
    }
}
