package advance.datastructures.amortisations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimpleDeque<T> {

    private SimpleStack<T> leftStack;
    private SimpleStack<T> rightStack;

    public SimpleDeque() {
        leftStack = new SimpleStack<>();
        rightStack = new SimpleStack<>();
    }

    public int getSize() {
        return leftStack.getSize() + rightStack.getSize();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void pushBack(T value) {
        leftStack.push(value);
    }

    public T popBack() {
        if (leftStack.isEmpty()) {
            rightStack = migrate(rightStack, leftStack);
        }
        return leftStack.pop();
    }

    public void pushFront(T value) {
        rightStack.push(value);
    }

    public T popFront() {
        if (rightStack.isEmpty()) {
            leftStack = migrate(leftStack, rightStack);
        }
        return rightStack.pop();
    }

    private SimpleStack<T> migrate(SimpleStack<T> from, SimpleStack<T> to) {
        SimpleStack<T> locale = from.reverse();
        for (int i = 0; i < (from.getSize() / 2) + 1; i++) {
            to.push(locale.pop());
        }
        return locale.reverse();
    }


    public void clear() {
        leftStack.clear();
        rightStack.clear();
    }

    public SimpleDeque<T> copy() {
        SimpleDeque<T> deque = new SimpleDeque<>();
        deque.rightStack = rightStack.copy();
        deque.leftStack = leftStack.copy();
        return deque;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{]");
        int count = 0;
        for (T value : rightStack.toList()) {
            builder.append(value.toString());
            count++;
            if (count < getSize()) {
                builder.append(", ");
            }
        }
        List<T> tail = leftStack.toList();
        Collections.reverse(tail);
        for (T value : tail) {
            builder.append(value.toString());
            count++;
            if (count < getSize()) {
                builder.append(", ");
            }
        }
        builder.append("[}");
        return builder.toString();
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[getSize()];
        T[] left = leftStack.toArray();
        T[] right = rightStack.toArray();
        for (int i = 0; i < getSize(); i++) {
            if (i < right.length) {
                result[i] = right[i];
            } else {
                result[i] = left[i - right.length];
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleDeque<?> that)) {
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
