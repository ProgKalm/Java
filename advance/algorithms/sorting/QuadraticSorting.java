package advance.algorithms.sorting;

import java.util.Objects;

public class QuadraticSorting {

    public static <T> void selectionSort(T[] array) {
        selectionSort(array, 0, array.length);
    }

    public static <T> void selectionSort(T[] array, int from, int to) {
        System.out.println(from + " " + to);
        for (int i = from; i < to - 1; i++) {
            int indexOfMinElem = i;
            for (int j = i + 1; j < to; j++) {
                if (compareIsLow(array[j], array[indexOfMinElem])) {
                    indexOfMinElem = j;
                }
            }
            swap(array, i, indexOfMinElem);
        }
    }

    public static <T> void bubbleSort(T[] array) {
        bubbleSort(array, 0, array.length);
    }

    public static <T> void bubbleSort(T[] array, int from, int to) {
        for (int i = from; i < to - 1; i++) {
            for (int j = from; j < to - 1; j++) {
                if (compareIsMore(array[j], array[j + 1])) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public static <T> void insertSort(T[] array) {
        insertSort(array, 0, array.length);
    }

    public static <T> void insertSort(T[] array, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            int j = i - 1;
            while (j >= from && compareIsMore(array[j], array[j + 1])) {
                swap(array, j, j + 1);
                j--;
            }
        }
    }

    private static <T> boolean compareIsLow(T first, T second) {
        return Objects.hashCode(first) < Objects.hashCode(second);
    }

    private static <T> boolean compareIsMore(T first, T second) {
        return Objects.hashCode(first) > Objects.hashCode(second);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
