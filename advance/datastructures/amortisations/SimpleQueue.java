package advance.datastructures.amortisations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SimpleQueue<T> {

    private SimpleStack<T> leftStack;
    private SimpleStack<T> rightStack;


    @SafeVarargs
    public SimpleQueue(T... values) {
        leftStack = new SimpleStack<T>(values);
        rightStack = new SimpleStack<T>();
    }

    public void push(T value) {
        leftStack.push(value);
    }

    public T pop() {
        if (rightStack.isEmpty()) {
            while (!leftStack.isEmpty()) {
                rightStack.push(leftStack.pop());
            }
        }
        return rightStack.pop();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void clear() {
        leftStack.clear();
        rightStack.clear();
    }

    public SimpleQueue<T> copy() {
        SimpleQueue<T> queue = new SimpleQueue<>();
        queue.rightStack = rightStack.copy();
        queue.leftStack = leftStack.copy();
        return queue;
    }

    public int getSize() {
        return leftStack.getSize() + rightStack.getSize();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("->");
        int count = 0;
        for (T value : leftStack.toList()) {
            builder.append(value.toString());
            count++;
            if (count < getSize()) {
                builder.append(", ");
            }
        }
        List<T> tail = rightStack.toList();
        Collections.reverse(tail);
        for (T value : tail) {
            builder.append(value.toString());
            count++;
            if (count < getSize()) {
                builder.append(", ");
            }
        }
        builder.append(" ->");
        return builder.toString();
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[getSize()];
        T[] left = leftStack.toArray();
        T[] right = rightStack.toArray();
        for (int i = 0; i < getSize(); i++) {
            if (i < left.length) {
                result[i] = left[i];
            } else {
                result[i] = right[i - left.length];
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleQueue<?> that)){
            return false;
        }
        return Arrays.equals(toArray(), that.toArray());
    }

    @Override
    public int hashCode() {
        int result = leftStack != null ? leftStack.hashCode() : 0;
        result = 31 * result + (rightStack != null ? rightStack.hashCode() : 0);
        return result;
    }
}
