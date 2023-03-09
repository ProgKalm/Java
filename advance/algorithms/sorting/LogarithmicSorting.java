package advance.algorithms.sorting;

import java.util.Objects;

public class LogarithmicSorting {

    public static <T> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <T> void quickSort(T[] array, int from, int to) {
        if (from < to) {
            int qPart = partition(array, from, to);
            quickSort(array, from, qPart);
            quickSort(array, qPart + 1, to);
        }
    }

    private static <T> int partition(T[] array, int from, int to) {
        T midElem = array[(from + to) / 2];
        int i = from;
        int j = to;
        while (i <= j) {
            while (compareIsLow(array[i], midElem)) {
                i++;
            }
            while (compareIsMore(array[j], midElem)) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(array, i++, j--);
        }
        return j;
    }

    public static <T> void mergeSort(T[] array) {
        mergeSort(array, 0, array.length);
    }

    public static <T> void mergeSort(T[] array, int from, int to) {
        if (from + 1 < to) {
            int mid = (from + to) / 2;
            mergeSort(array, from, mid);
            mergeSort(array, mid, to);
            merge(array, from, mid, to);
        }
    }

    private static <T> void merge(T[] array, int from, int mid, int to) {
        int indexFirst = 0;
        int indexSecond = 0;
        T[] result = (T[]) new Object[to - from];
        while (((from + indexFirst) < mid) && ((mid + indexSecond) < to)) {
            if (compareIsLow(array[from + indexFirst], array[mid + indexSecond])) {
                result[indexFirst + indexSecond] = array[from + indexFirst];
                indexFirst++;
            } else {
                result[indexFirst + indexSecond] = array[mid + indexSecond];
                indexSecond++;
            }
        }

        while ((from + indexFirst) < mid) {
            result[indexFirst + indexSecond] = array[from + indexFirst];
            indexFirst++;
        }
        while ((mid + indexSecond) < to) {
            result[indexFirst + indexSecond] = array[mid + indexSecond];
            indexSecond++;
        }

        for (int i = 0; i < (indexFirst + indexSecond); i++) {
            array[from + i] = result[i];
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
