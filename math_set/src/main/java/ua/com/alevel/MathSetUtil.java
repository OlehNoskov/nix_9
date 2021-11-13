package ua.com.alevel;

import java.math.BigDecimal;
import java.util.Arrays;

public class MathSetUtil<NumberSet extends Number> {

    private Number[] setNumbers;
    private final int DEFAULT_CAPACITY = 10;
    private int size = 0;
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

    public MathSetUtil(MathSetUtil... mathSetUtils) {
        setNumbers = new Number[DEFAULT_CAPACITY];
        for (int i = 0; i < setNumbers.length; i++) {
            Number[] arrayNumbers = mathSetUtils[i].toArray();
            for (int j = 0; j < arrayNumbers.length; j++) {
                add(arrayNumbers[j]);
            }
        }
    }

    public void add(Number n) {
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

    public void add(Number... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    public void join(MathSetUtil... mathSetUtil) {
        for (int i = 0; i < mathSetUtil.length; i++) {
            Number[] newArrayNumbers = mathSetUtil[i].toArray();
            for (int j = 0; j < newArrayNumbers.length; j++) {
                add(newArrayNumbers[j]);
            }
        }
    }

    public void intersection(MathSetUtil<NumberSet> mathSet) {
        int mathSetLength = Math.max(this.setNumbers.length, mathSet.size());
        Number[] mathSetNew = new Number[mathSetLength];
        Number[] mathSetIntersection = mathSet.toArray();
        int mathSetNewIndex = 0;
        for (Number number : this.setNumbers) {
            for (Number value : mathSetIntersection) {
                if (compareNumber((NumberSet) number, (NumberSet) value) == 0) {
                    mathSetNew[mathSetNewIndex] = number;
                }
            }
        }
        this.setNumbers = mathSetNew;
    }

    public void intersection(MathSetUtil... mathSets) {
        for (MathSetUtil mathSet : mathSets) {
            intersection(mathSet);
        }
    }

    public void sortDesc() {
        sortDesc(1, setNumbers.length - 1);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
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

    public void sortDesc(Number value) {
        if (setNumbers.length != 0) {
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
    }

    public void sortAsc() {
        sortAsc(1, setNumbers.length - 1);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (setNumbers.length != 0) {
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
    }

    public void sortAsc(Number value) {
        if (setNumbers.length != 0) {
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
    }

    public Number get(int index) {
        if (setNumbers.length != 0)
            return setNumbers[index];
        else {
            return 0;
        }
    }

    public Number getMax() {
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

    public Number getMin() {
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

    public Number getAverage() {
        double average = 0;
        int sizeArrayNumbers = setNumbers.length;
        for (int i = 0; i < sizeArrayNumbers; i++)
            average += setNumbers[i].doubleValue();
        return average / sizeArrayNumbers;
    }

    public Number getMedian() {
        MathSetUtil mathSetUtil = new MathSetUtil(setNumbers);
        int medianIndex = setNumbers.length / 2;
        Number[] newArraySortAsxNumbers = mathSetUtil.setNumbers;
        return newArraySortAsxNumbers[medianIndex];
    }

    public Number[] toArray() {
        Number[] toArrayNumbers = new Number[setNumbers.length];
        for (int i = 0; i < setNumbers.length; i++) {
            toArrayNumbers[i] = setNumbers[i];
        }
        return toArrayNumbers;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] toArrayNumbers = new Number[setNumbers.length];
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            toArrayNumbers[i] = setNumbers[i];
        }
        return toArrayNumbers;
    }

    public MathSetUtil cut(int firstIndex, int lastIndex) {
        Number[] cutArrayNumbers = new Number[lastIndex + 1 - firstIndex];
        int countSizeCutArray = 0;
        for (int i = firstIndex; i < lastIndex + 1 && i < setNumbers.length; i++) {
            setNumbers[i] = cutArrayNumbers[countSizeCutArray];
            countSizeCutArray++;
        }
        return new MathSetUtil(cutArrayNumbers);
    }

    public void clear() {
        setNumbers = null;
    }

    public void clear(Number[] numbers) {
        for (Number number : numbers) {
            number = null;
        }
        numbers = EMPTY_ARRAY_NUMBERS;
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

    private int compareNumber(Number e1, Number e2) {
        return new BigDecimal(e1.toString()).compareTo(new BigDecimal(e2.toString()));
    }

    public int size() {
        return this.setNumbers.length;
    }

    @Override
    public String toString() {
        return "MathSetUtil " +
                "setNumbers: " + Arrays.toString(setNumbers);
    }
}