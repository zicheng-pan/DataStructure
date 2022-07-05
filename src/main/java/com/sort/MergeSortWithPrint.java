package com.sort;

import java.util.Arrays;

public class MergeSortWithPrint {
    private MergeSortWithPrint() {

    }

    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length - 1, 0);
    }

    private static <E extends Comparable<E>> void sort(E[] array, int l, int r, int layrnumber) {

        System.err.println(generatespace(layrnumber) + layrnumber + " befor merge: from " + l + " to " + r);
        System.err.println(generatespace(layrnumber) + sout(array));

        if (l >= r) return;
        int mid = (r + l) / 2;
        sort(array, l, mid, layrnumber + 1);
        sort(array, mid + 1, r, layrnumber + 1);
        merge(array, l, mid, r, layrnumber + 1);

        System.out.println(generatespace(layrnumber) + layrnumber + " end merge: from " + l + ", to " + r);
        System.out.println(generatespace(layrnumber) + sout(array));
    }

    private static <E extends Comparable<E>> void merge(E[] array, int l, int mid, int r, int layernumber) {
        E[] temparray = Arrays.copyOfRange(array, l, r + 1);
        int i = 0;
        int j = mid + 1 - l;
        for (int index = l; index <= r; index++) {
            if (i > mid - l) {
                array[index] = temparray[j];
                j = j + 1;
            } else if (j > temparray.length - 1) {
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

    public static <E extends Comparable<E>> String sout(E[] array) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1)
                result.append(array[i]);
            else
                result.append(array[i] + ", ");
        }
        return result.toString();
    }

    private static String generatespace(int index) {
        String str = "";
        for (int i = 0; i < index; i++) {
            str = str + "      ";
        }
        return str;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 3, 4, 10, 6, 3, 2, 24, 12};
        MergeSortWithPrint.sort(array);
    }
}
