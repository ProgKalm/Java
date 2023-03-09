package advance.datastructures.amortisations;

import java.util.Arrays;

public class DynamicArray<T> {

    private int currentSize;
    private T[] array;
    private final int differenceToReduce = 4;
    private final int resizeVar = 2;

    @SafeVarargs
    public DynamicArray(T... elements) {
        currentSize = elements.length;
        array = elements;
        if (array.length == 0) {
            array = Arrays.copyOf(array, 1);
        } else {
            resize(true);
        }

    }

    public T get(int index) {
        if (isValidIndex(index)) {
            return array[index];
        }
        return null;
    }

    public void set(int index, T value) {
        if (isValidIndex(index)) {
            array[index] = value;
        } else if (index == currentSize) {
            add(value);
        } else {
            throw new ArrayIndexOutOfBoundsException(
                    ("Try set value '%s' by index '%d', " +
                            "which more than DynamicArray size.").formatted(value.toString(), index)
            );
        }
    }

    public void add(T value) {
        if (currentSize == array.length) {
            resize(true);
        }
        array[currentSize] = value;
        currentSize++;
    }

    public T del() {
        if (currentSize > 0) {
            T result = get(size() - 1);
            currentSize--;
            if (currentSize * differenceToReduce >= array.length) {
                resize(false);
            }
            return result;
        }
        return null;
    }

    public int size() {
        return currentSize;
    }

    public int reservedElementCount() {
        return array.length;
    }

    public void clear() {
        currentSize = 0;
        array = Arrays.copyOf(array, 1);
    }

    public DynamicArray<T> copy() {
        return new DynamicArray<>(array);
    }

    private void resize(boolean isIncrease) {
        if (isIncrease) {
            array = Arrays.copyOf(array, resizeVar * array.length);
        } else {
            array = Arrays.copyOf(array, ((int) (array.length / resizeVar)));
        }
    }

    private boolean isValidIndex(int index) {
        return (index >= 0) && (index < currentSize);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, 0, currentSize));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DynamicArray<?> that)) {
            return false;
        }
        if (currentSize != that.currentSize) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = currentSize;
        result = 31 * result + Arrays.hashCode(array);
        result = 31 * result + differenceToReduce;
        result = 31 * result + resizeVar;
        return result;
    }
}
