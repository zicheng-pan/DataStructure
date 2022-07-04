package com.sort;

import java.util.Arrays;

public class MergeSort {
    private MergeSort() {

    }

    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] array, int l, int r) {
        if (l >= r) return;
        int mid = (r + l) / 2;
        sort(array, l, mid);
        sort(array, mid + 1, r);
        merge(array, l, mid, r);
    }

    private static <E extends Comparable<E>> void merge(E[] array, int l, int mid, int r) {
        E[] temparray = Arrays.copyOfRange(array, l, r + 1);
        int i = 0;
        int j = mid + 1 - l;
        for (int index = l; index <= r; index++) {
            if (i > mid) {
                array[index] = temparray[j];
                j = j + 1;
            } else if (j > r - l) {
                array[index] = temparray[i];
                i = i + 1;
            } else if (temparray[i].compareTo(temparray[j]) <= 0) {
                array[index] = temparray[i];
                i = i + 1;
            } else {
                array[index] = temparray[j];
                j = j + 1;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 3, 4, 10, 6, 3, 2, 24, 12};
        MergeSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
