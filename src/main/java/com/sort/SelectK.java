package com.sort;

import java.util.Random;

public class SelectK {


    public static <E extends Comparable<E>> void main(String[] args) {
        int k = 4;
        Integer[] array = new Integer[]{1, 6, 4, 2, 5, 3};
        Random random = new Random();
        int result = SelectK.selectK(array, 0, array.length - 1, k, random);
        System.out.println(result);
    }

    private static <E extends Comparable<E>> E selectK(E[] array, int left, int right, int k, Random random) {
//        int left = 0;
//        int right = array.length - 1;
        int index = partition(array, left, right, random);
        if (index == k) {
            return array[index];
        } else if (index < k) {
            return selectK(array, index + 1, right, k, random);
        } else {
            return selectK(array, left, index - 1, k, random);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int left, int right, Random random) {
        int random_index = random.nextInt(right - left + 1);
        swap(array, left, left + random_index);
        int l = left + 1;
        int r = right;
        int j = left + 1;
        E e = array[left];
        // left + 1 .. l < e
        // l .. j = e
        // r .. right > e
        // 循环结束的条件 j > r
        while (j <= r) {
            int result = array[j].compareTo(e);
            if (result < 0) {
                l = l + 1;
                j = j + 1;
            } else if (result == 0) {
                j = j + 1;
            } else {
                swap(array, r, j);
                j = j + 1;
                r = r - 1;
            }
        }
        swap(array, left, j - 1);
        return j - 1;
    }

    private static <E> void swap(E[] array, int left, int i) {
        E item = array[i];
        array[i] = array[left];
        array[left] = item;
    }


}
