//package ua.com.alevel;
//
//public class MathSetUtil<Number> {
//
//    private int capacity;
//    private Number[] numbers;
//    private MathSetUtil mathSetNumbers;
//    private Number n;
//
//    MathSetUtil() {
//    }
//
//    MathSetUtil(int capacity) {
//        this.capacity = capacity;
//    }
//
//    MathSetUtil(Number[] numbers) {
//        this.numbers = numbers;
//    }
//
//    MathSetUtil(Number[]... numbers) {
//    }
//
//    MathSetUtil(MathSetUtil mathSetNumbers) {
//        this.mathSetNumbers = mathSetNumbers;
//    }
//
//
//    MathSetUtil(MathSetUtil... numbers) {
//
//    }
//    //Methods
//
//    void add(Number n) {
//
//    }
//
//    void add(Number... n) {
//
//    }
//
//    void join(MathSetUtil mathSetUtil) {
//    }
//
//    void join(MathSetUtil... mathSetUtil) {
//    }
//
//    void intersection(MathSetUtil mathSetUtil) {
//    }
//
//    void intersection(MathSetUtil... mathSetUtil) {
//    }
//
//    void sortDesc() {
//    }
//
//    void sortDesc(int firstIndex, int lastIndex) {
//    }
//
//    void sortDesc(Number value) {
//    }
//
//    void sortAsc() {
//    }
//
//    void sortAsc(int firstIndex, int lastIndex) {
//    }
//
//    void sortAsc(Number value) {
//    }
//
//    Number get(int index) {
//        for (int i = 0; i < numbers.length; i++) {
//            if (i == index)
//                return numbers[index];
//        }
//    }
//
//    Number getMax() {
//        Number result = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            result = numbers[0];
//            if (result < numbers[i])
//                result = numbers[i];
//        }
//        return result;
//    }
//
//    Number getMin() {
//
//        return null;
//    }
//
//    Number getAverage() {
//        return null;
//    }
//
//    Number getMedian() {
//        return null;
//    }
//
//    Number[] toArray() {
//        return null;
//    }
//
//    Number[] toArray(int firstIndex, int lastIndex) {
//        return null;
//    }
//
//    MathSetUtil cut(int firstIndex, int lastIndex) {
//        return null;
//    }
//
//    void clear() {
//    }
//
//    void clear(Number[] numbers) {
//    }
//}