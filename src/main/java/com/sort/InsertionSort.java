package com.sort;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

    private InsertionSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] array) {
        // 插入排序
        // 获取
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j - 1 > 0; j--) {
                if (array[j - 1].compareTo(array[j]) > 0)
                    swap(array, j - 1, j);
                else
                    break;
            }
        }
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <E> void printArr(E[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 10;
        Random random = new Random();
        Integer[] array = new Integer[num];

        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(num);
        }

        printArr(array);
        InsertionSort.sort(array);
        printArr(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
            if (i + 1 < array.length) {
                if (array[i] > array[i + 1])
                    throw new RuntimeException("sort error!!");
            }
        }
    }

}
