package advance.datastructures.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BinaryHeap<T> {

    private final ArrayList<T> array;

    public BinaryHeap() {
        array = new ArrayList<>();
    }

    public BinaryHeap(T[] values) {
        array = new ArrayList<>();
        array.addAll(Arrays.asList(values));
        buildHeap();
    }

    public BinaryHeap(List<T> values) {
        array = new ArrayList<>(values);
        buildHeap();
    }

    public void add(T key) {
        array.add(key);
        siftUp(getSize() - 1);
    }

    public T extractMin() {
        if (getSize() == 0) {
            return null;
        }
        T min = array.get(0);
        array.set(0, array.get(getSize() - 1));
        array.remove(getSize() - 1);
        siftDown(0);
        return min;
    }

    public int getSize() {
        return array.size();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void clear() {
        array.clear();
    }

    public BinaryHeap<T> merge(BinaryHeap<T> heap) {
        BinaryHeap<T> resultHeap = copy();
        heap = heap.copy();
        while (!heap.isEmpty()) {
            resultHeap.add(heap.extractMin());
        }
        return resultHeap;
    }

    public BinaryHeap<T> copy() {
        return new BinaryHeap<>(array);
    }

    private void buildHeap() {
        for (int i = getSize() - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {
        while (2 * index + 1 < getSize()) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int newIndex = left;
            if (right < getSize() && compareIsLow(right, left)) {
                newIndex = right;
            }
            if (compareIsMore(newIndex, index)) {
                break;
            }
            swap(index, newIndex);
            index = newIndex;
        }
    }

    private void siftUp(int index) {
        while (compareIsLow(index, (index - 1) / 2)) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int from, int to) {
        T a = array.get(from);
        T b = array.get(to);
        array.set(from, b);
        array.set(to, a);
    }

    private boolean compareIsMore(int first, int second) {
        int firstHash = Objects.hashCode(array.get(first));
        int secondHash = Objects.hashCode(array.get(second));
        return firstHash > secondHash;
    }

    private boolean compareIsLow(int first, int second) {
        int firstHash = Objects.hashCode(array.get(first));
        int secondHash = Objects.hashCode(array.get(second));
        return firstHash < secondHash;
    }

    @Override
    public String toString() {
        return "BinaryHeap" + array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BinaryHeap<?> that)) {
            return false;
        }

        return Objects.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return array != null ? array.hashCode() : 0;
    }

    public List<T> toList() {
        return new ArrayList<>(array);
    }

    public T[] toArray() {
        T[] array = (T[]) this.array.toArray(new Object[0]);
        System.out.println(Arrays.toString(array));
        return array;
    }

    public static <T> T[] heapSort(T[] array) {
        BinaryHeap<T> heap = new BinaryHeap<>(array);
        return heap.toArray();
    }

    public static <T> List<T> heapSort(List<T> array) {
        BinaryHeap<T> heap = new BinaryHeap<>(array);
        return heap.toList();
    }
}
