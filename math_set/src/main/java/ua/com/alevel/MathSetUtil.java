package ua.com.alevel;

import java.util.Arrays;

public class MathSetUtil<T extends Number> {

    private Number[] setNumbers;
    private final int DEFAULT_CAPACITY = 10;
    private static final Number[] EMPTY_ARRAY_NUMBERS = {};

    public MathSetUtil() {
        setNumbers = new Number[DEFAULT_CAPACITY];
    }

    public MathSetUtil(int initialCapacity) {
        if (initialCapacity > 0) {
            this.setNumbers = new Number[initialCapacity];
        } else if (initialCapacity == 0) {
            this.setNumbers = EMPTY_ARRAY_NUMBERS;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MathSetUtil(Number[] setNumbers) {
        this.setNumbers = setNumbers;
    }

    public MathSetUtil(Number[]... numbers) {
        int incomingLenghtArray = 0;
        int indexNewArray = 0;

        for (int i = 0; i < numbers.length; i++) {
            for (int b = 0; b < numbers[i].length; b++) {
                if (numbers[i][b] != null)
                    incomingLenghtArray++;
            }
        }
        this.setNumbers = new Number[incomingLenghtArray];
        for (int i = 0; i < numbers.length; i++) {
            for (int b = 0; b < numbers[i].length; b++) {
                setNumbers[indexNewArray] = numbers[i][b];
                indexNewArray++;
            }
        }
    }

    public MathSetUtil(MathSetUtil mathSetNumbers) {

    }


//    public MathSetUtil(MathSetUtil... mathSetUtils) {
//        setNumbers = new Number[DEFAULT_CAPACITY];
//
//        for (int i = 0; i < setNumbers.length; i++) {
//            Number[] arrNum = setNumbers[i].toArray();
//            for (int j = 0; j < arrNum.length; j++) {
//                add(arrNum[j]);
//            }
//        }
//    }

    //DONE
    void add(Number n) {
        int countSizeNumbersArray = 0;
        for (Number number : setNumbers) {
            if (number != null)
                countSizeNumbersArray++;
        }
        if (countSizeNumbersArray == setNumbers.length) {
            Number[] newArrayNumbers = new Number[countSizeNumbersArray + 1];
            for (int i = 0; i < setNumbers.length; i++) {
                newArrayNumbers[i] = setNumbers[i];
            }
            setNumbers = newArrayNumbers;
        }
        if (!containsNumbers(n)) {
            setNumbers[countSizeNumbersArray] = n;
        }
    }

    void add(Number... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    void join(MathSetUtil mathSetUtil) {
    }

    void join(MathSetUtil... mathSetUtil) {
    }

    void intersection(MathSetUtil mathSetUtil) {
    }

    void intersection(MathSetUtil... mathSetUtil) {
    }
    //DONE
    void sortDesc() {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < setNumbers.length; i++) {
                if (setNumbers[i].doubleValue() > setNumbers[i - 1].doubleValue()) {
                    swap(setNumbers, i, i - 1);
                    needIteration = true;
                }
            }
        }
    }
    //DONE
    void sortDesc(int firstIndex, int lastIndex) {
        if (setNumbers.length != 0) {
            boolean needIteration = true;
            while (needIteration) {
                needIteration = false;
                for (int i = firstIndex; i < lastIndex + 1 && i < setNumbers.length; i++) {
                    if (setNumbers[i].doubleValue() > setNumbers[i - 1].doubleValue()) {
                        swap(setNumbers, i, i - 1);
                        needIteration = true;
                    }
                }
            }
        }
    }
    //DONE
    void sortDesc(Number value) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < setNumbers.length; i++) {
                if (setNumbers[i].doubleValue() == value.doubleValue()) {
                    sortDesc(i, setNumbers.length);
                }
            }
        }
    }
    //DONE
    void sortAsc() {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < setNumbers.length; i++) {
                if (setNumbers[i].doubleValue() < setNumbers[i - 1].doubleValue()) {
                    swap(setNumbers, i, i - 1);
                    needIteration = true;
                }
            }
        }
    }
    //DONE
    void sortAsc(int firstIndex, int lastIndex) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = firstIndex; i < lastIndex + 1 && i < setNumbers.length; i++) {
                if (setNumbers[i].doubleValue() < setNumbers[i - 1].doubleValue()) {
                    swap(setNumbers, i, i - 1);
                    needIteration = true;
                }
            }
        }
    }
    //DONE
    void sortAsc(Number value) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < setNumbers.length; i++) {
                if (setNumbers[i].doubleValue() == value.doubleValue()) {
                    sortAsc(i, setNumbers.length);
                }
            }
        }
    }
    //DONE
    Number get(int index) {
        if (setNumbers.length != 0)
            return setNumbers[index];
        else {
            return 0;
        }
    }

    //DONE
    Number getMax() {
        double maxNumber;
        if (setNumbers.length == 0) {
            return 0;
        } else
            maxNumber = setNumbers[0].doubleValue();
        for (int i = 1; i < setNumbers.length; i++)
            if (setNumbers[i].doubleValue() > maxNumber)
                maxNumber = setNumbers[i].doubleValue();
        return maxNumber;
    }

    //DONE
    Number getMin() {
        double minNumber;
        if (setNumbers.length == 0) {
            return 0;
        } else
            minNumber = setNumbers[0].doubleValue();
        for (int i = 1; i < setNumbers.length; i++)
            if (setNumbers[i].doubleValue() < minNumber)
                minNumber = setNumbers[i].doubleValue();
        return minNumber;
    }

    //DONE
    Number getAverage() {
        double average = 0;
        int sizeArrayNumbers = setNumbers.length;
        for (int i = 0; i < sizeArrayNumbers; i++)
            average += setNumbers[i].doubleValue();
        return average / sizeArrayNumbers;
    }

    //DONE
    Number getMedian() {
        MathSetUtil mathSetUtil = new MathSetUtil(setNumbers);
        int medianIndex = setNumbers.length / 2;
        Number[] newArraySortAsxNumbers = mathSetUtil.setNumbers;
        return newArraySortAsxNumbers[medianIndex];
    }

    //DONE
    Number[] toArray() {
        Number[] toArrayNumbers = new Number[setNumbers.length];
        for (int i = 0; i < setNumbers.length; i++) {
            toArrayNumbers[i] = setNumbers[i];
        }
        return toArrayNumbers;
    }

    //DONE
    Number[] toArray(int firstIndex, int lastIndex) {
        Number[] toArrayNumbers = new Number[setNumbers.length];
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            toArrayNumbers[i] = setNumbers[i];
        }
        return toArrayNumbers;
    }

    //DONE
    MathSetUtil cut(int firstIndex, int lastIndex) {
        Number[] cutArrayNumbers = new Number[lastIndex + 1 - firstIndex];
        int countSizeCutArray = 0;
        for (int i = firstIndex; i < lastIndex + 1 && i < setNumbers.length; i++) {
            setNumbers[i] = cutArrayNumbers[countSizeCutArray];
            countSizeCutArray++;
        }
        return new MathSetUtil(cutArrayNumbers);
    }

    //DONE
    void clear() {
        setNumbers = null;
    }
//DONE
    void clear(Number[] numbers) {
        for (Number number : numbers) {
            number = null;
        }
        numbers = new Number[DEFAULT_CAPACITY];
    }

    private boolean containsNumbers(Number n) {
        for (Number number : setNumbers) {
            if (number == n) {
                return true;
            }
        }
        return false;
    }

    private void swap(Number[] array, int ind1, int ind2) {
        Number temporary = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = temporary;
    }

    @Override
    public String toString() {
        return "MathSetUtil{" +
                "setNumbers=" + Arrays.toString(setNumbers) +
                '}';
    }
}