package projects.math.bignumbers;

import projects.math.bignumbers.exceptions.BigNumberException;
import projects.math.bignumbers.exceptions.InvalidNumberFormatException;

import java.util.*;

public class LongNumber {
    /*Класс реализующий длинную арифметику больших целых чисел*/

    private final int DEGREE = 3;
    private final List<Integer> numArray;
    private boolean sign = false;

    public LongNumber(String number) throws BigNumberException {
        numArray = new LinkedList<>();
        parseString(number);
    }

    public LongNumber(Number number) throws BigNumberException {
        numArray = new LinkedList<>();
        parseString(number.toString());
    }

    public LongNumber abs() {
        sign = false;
        return this;
    }

    public static LongNumber abs(final LongNumber number) throws BigNumberException {
        LongNumber clone = number.copy();
        clone.sign = false;
        return clone;
    }

    public LongNumber negate() {
        sign = !sign;
        return this;
    }

    public static LongNumber negate(final LongNumber number) throws BigNumberException {
        LongNumber clone = number.copy();
        clone.sign = !clone.sign;
        return clone;
    }

    public LongNumber add(final LongNumber number) throws BigNumberException {
        if (isNegate() != number.isNegate()) {
            if (number.isNegate()) {
                return subtract(LongNumber.abs(number));
            }
            LongNumber sub = number.copy().subtract(LongNumber.abs(this));
            numArray.clear();
            numArray.addAll(sub.numArray);
            sign = sub.isNegate();
            removeLeadZeroes();
            return this;
        }
        int base = (int) Math.pow(10, DEGREE);
        int spValue = 0;
        int len = Math.max(numArray.size(), number.numArray.size());
        for (int i = 0; i < len; i++) {
            int value = getBit(i) + number.getBit(i) + spValue;
            setBit(i, value % base);
            spValue = value / base;
        }
        if (spValue > 0) {
            setBit(len + 1, spValue);
        }
        removeLeadZeroes();
        return this;
    }

    public LongNumber subtract(final LongNumber number) throws BigNumberException {
        if (isNegate() != number.isNegate()) {
            return add(LongNumber.negate(number));
        } else if (isNegate()) {
            final LongNumber sub = number.copy().abs().subtract(copy().abs());
            sign = sub.sign;
            numArray.clear();
            numArray.addAll(sub.numArray);
            removeLeadZeroes();
            return this;
        } else if (lowThan(number)) {
            final LongNumber sub = number.copy().subtract(copy()).negate();
            sign = sub.sign;
            numArray.clear();
            numArray.addAll(sub.numArray);
            removeLeadZeroes();
            return this;
        }
        int base = (int) Math.pow(10, DEGREE);
        int deposit = 0;
        int len = Math.max(numArray.size(), number.numArray.size());
        for (int i = 0; i < len; i++) {
            int value = (getBit(i) - deposit) - number.getBit(i);
            deposit = (value < 0) ? 1 : 0;
            value = (value < 0) ? base + value : value;
            setBit(i, value % base);
        }
        removeLeadZeroes();
        return this;
    }

    public static LongNumber sum(final LongNumber... numbers) throws BigNumberException {
        LongNumber result = new LongNumber("0");
        for (LongNumber number : numbers) {
            result.add(number);
        }
        return result;
    }

    public LongNumber multiply(final LongNumber number) throws BigNumberException {
        long base = (long) Math.pow(10, DEGREE);
        sign = sign != number.sign;
        int[] resultArray = new int[number.numArray.size() + numArray.size()];
        for (int i = 0; i < numArray.size(); i++) {
            long carry = 0;
            for (int j = 0; j < number.numArray.size() || carry != 0; j++) {
                long cur = resultArray[i + j] + (long) getBit(i) * number.getBit(j) + carry;
                resultArray[i + j] = (int) (cur % base);
                carry = cur / base;
            }
        }
        numArray.clear();
        numArray.addAll(toList(resultArray));
        removeLeadZeroes();
        return this;
    }

    public static LongNumber multiply(final LongNumber... numbers) throws BigNumberException {
        if (numbers.length == 0) {
            return new LongNumber("0");
        }
        LongNumber result = new LongNumber("1");
        for (LongNumber number: numbers) {
            result.multiply(number);
        }
        return result;
    }

    /*
    Return {-1, 0, 1}
    If this > number: 1;
    If this == number: 0; - it is equals; 
    If this < number: -1;
    */
    public int compareTo(final LongNumber number) {
        if (number.isNegate() != isNegate()) {
            return isNegate() ? -1 : 1;
        } else if (numArray.size() != number.numArray.size()) {
            return (numArray.size() > number.numArray.size()) ? 1 : -1;
        }
        for (int i = numArray.size() - 1; i >= 0; i--) {
            if (getBit(i) != number.getBit(i)) {
                return (getBit(i) > number.getBit(i)) ? 1 : -1;
            }
        }
        return 0;
    }

    /*
    Return is this < number;
    */
    public boolean lowThan(final LongNumber number) {
        return compareTo(number) == -1;
    }

    /*
    Return is this > number;
    */
    public boolean moreThan(final LongNumber number) {
        return compareTo(number) == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LongNumber number)) {
            return false;
        }
        if (sign != number.sign) {
            return false;
        }
        return Objects.equals(numArray, number.numArray);
    }

    @Override
    public int hashCode() {
        int result = DEGREE;
        result = 31 * result + (numArray != null ? numArray.hashCode() : 0);
        result = 31 * result + (sign ? 1 : 0);
        return result;
    }

    public LongNumber copy() throws BigNumberException {
        return new LongNumber(toString());
    }

    public boolean isNegate() {
        return sign;
    }

    private void removeLeadZeroes() {
        int i = numArray.size() - 1;
        while (i > 0 && numArray.get(i).equals(0)) {
            numArray.remove(i);
            i--;
        }
    }

    private void parseString(String number) throws BigNumberException {
        if (number == null || number.equals("")){
            throw new InvalidNumberFormatException(number);
        }
        number = number.replace("+", "");
        sign = number.charAt(0) == '-' && getCount('-', number) % 2 == 1;
        String n = number;
        number = number.replace("-", "");
        if (!isNumber(number) || number.equals("")) {
            if (number.equals("")) {
                number = n;
            }
            throw new InvalidNumberFormatException(number);
        }
        while (!number.equals("")) {
            String[] pair = getBitString(number);
            number = pair[0];
            numArray.add(Integer.parseInt(pair[1]));
        }
        removeLeadZeroes();
    }

    private int getCount(char c, String number) {
        int count = 0;
        for (int i = 0; i < number.length(); i++) {
            count += (number.charAt(i) == c ? 1 : 0);
        }
        return count;
    }

    private Collection<Integer> toList(int[] resultArray) {
        List<Integer> list = new LinkedList<>();
        for (int i : resultArray) {
            list.add(i);
        }
        return list;
    }

    private boolean isNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private String[] getBitString(String number) {
        if (DEGREE >= number.length()) {
            return new String[]{"", number};
        }
        return new String[]{
                number.substring(0, number.length() - DEGREE),
                number.substring(number.length() - DEGREE)
        };
    }

    private int getBit(int index) {
        if (index >= numArray.size()) {
            return 0;
        }
        return numArray.get(index);
    }

    private void setBit(int index, int value) {
        if (index >= numArray.size()) {
            numArray.add(value);
        }
        numArray.set(index, value);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int bit = numArray.size() - 1; bit >= 0; bit--) {
            if (bit != numArray.size() - 1) {
                builder.append(bitToString(numArray.get(bit)));
            } else {
                builder.append(numArray.get(bit));
            }
        }
        builder.insert(0, sign ? "-" : "");
        return builder.toString();
    }

    private String bitToString(int bit) {
        StringBuilder bitString = new StringBuilder(Integer.toString(bit));
        while (bitString.length() < DEGREE) {
            bitString.insert(0, "0");

        }
        return bitString.toString();
    }
}
