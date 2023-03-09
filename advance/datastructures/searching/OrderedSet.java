package advance.datastructures.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderedSet<T> {

    private List<T> array;

    @SafeVarargs
    public OrderedSet(T... elements) {
        array = new ArrayList<>();
        buildSet(elements);
    }

    public OrderedSet(List<T> elements) {
        array = new ArrayList<>();
        buildSet((T[]) elements.toArray());
    }

    public void add(T elem) {
        if (search(elem)) {
            array.add(elem);
            int index = getSize() - 1;
            while (compareIsLow(index, index - 1)) {
                swap(index, index - 1);
                index = index - 1;
            }
        }
    }

    public void delete(T elem) {
        array.remove(elem);
    }

    public boolean search(T elem) {
        return array.contains(elem);
    }

    public T getMinimal() {
        return get(0);
    }

    public T getMaximal() {
        return get(getSize() - 1);
    }

    private boolean isMore(T elem1, T elem2) {
        return Objects.hashCode(elem1) > Objects.hashCode(elem2);
    }

    private boolean isLow(T elem1, T elem2) {
        return Objects.hashCode(elem1) < Objects.hashCode(elem2);
    }

    public int getSize() {
        return array.size();
    }

    private T get(int index) {
        if (isValidIndex(index)) {
            return array.get(index);
        }
        return null;
    }


    private void buildSet(T[] elements) {

    }

    private void swap(int first, int second) {
        if (isValidIndex(first) && isValidIndex(second)) {
            T firstObj = array.get(first);
            T secondObj = array.get(second);
            array.set(first, secondObj);
            array.set(second, firstObj);
        }
    }

    private boolean compareIsLow(int first, int second) {
        if (isValidIndex(first) && isValidIndex(second)) {
            int firstHash = Objects.hashCode(array.get(first));
            int secondHash = Objects.hashCode(array.get(second));
            return firstHash < secondHash;
        }
        return false;
    }

    private boolean isValidIndex(int index) {
        return 0 <= index && index < getSize();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        int count = 0;
        for (T elem : array) {
            sb.append(elem);
            count += 1;
            if (count != getSize()) {
                sb.append(", ");
            }

        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderedSet<?> that)) {
            return false;
        }
        return Objects.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return array != null ? array.hashCode() : 0;
    }
}
