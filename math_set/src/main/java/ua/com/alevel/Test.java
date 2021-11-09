package ua.com.alevel;

import java.util.Arrays;

;

public class Test {
    public static void main(String[] args) {
        Number[] numbers = new Number[]{-2000,10,2,1000,1000,500,1000,-5,80};
        MathSetUtil mathSetUtil = new MathSetUtil(numbers);
        mathSetUtil.add(20);
        mathSetUtil.add(50,20,1);
        System.out.println(mathSetUtil.toString());

        mathSetUtil.clear(numbers);
        System.out.println(Arrays.toString(numbers));

    }
}
