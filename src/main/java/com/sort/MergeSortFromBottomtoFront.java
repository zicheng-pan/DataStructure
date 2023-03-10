package com.sort;

import java.util.Arrays;

public class MergeSortFromBottomtoFront<E extends Comparable<E>> {

    // 代码想法不同，我这里是成对成对安排的，i 是合并之后的总数组所以需要计算mid的位置，相对于i当做合并之前的数组复杂
    public void sort(E[] arrray) {
        for (int i = 2; i <= arrray.length * 2; i = i * 2) {
            for (int j = 0; j < arrray.length; j = j + i) {
                int l = j;
                int mid = j + i / 2;
                int r = j + i - 1;
                if (r >= arrray.length)
                    r = arrray.length - 1;
                merge(arrray, l, mid, r);
            }
        }
    }

    public void sort2(E[] array) {
        E[] temp = Arrays.copyOf(array, array.length);
        int n = array.length;

        // sz 表示合并的区间长度，第一轮表示合并区间长度是1，那么第一次的合并就是2个长度为1的数组进行合并，终止条件
        // 当整个合并后的数组长度都在左侧，那么表示合并结束
        for (int sz = 1; sz < n; sz += sz) {
            // 遍历合并的两个区间的起始位置 i
            // 合并 [i, i + sz - 1], [i + sz, i + sz + sz -1] 这两个区间
            // 终止条件当没有右区间的时候，可以结束，i + sz 只要< n 那么就一定有右区间
            for (int i = 0; i + sz < n; i += sz + sz)
                // 其中需要注意的是，之前的代码merge方法mid都是左区间的最后一个元素，而本类中的merge方法是有区间的第一个元素
                // 所以这里的mid是 i + sz，刚好符合上面的区间定义
                merge(array, i, i + sz, Math.min(i + sz + sz - 1, n - 1));
        }
    }

    public E[] merge(E[] array, int l, int mid, int r) {
        E[] copy_temp = Arrays.copyOfRange(array, 0, array.length);
        int index_a = l;
        int index_b = mid;
        for (int i = l; i <= r; i++) {
            if (index_a >= mid && index_b <= r) {
                array[i] = copy_temp[index_b];
                index_b = index_b + 1;
            } else if (index_a < mid && index_b > r) {
                array[i] = copy_temp[index_a];
                index_a = index_a + 1;
            } else if (index_a < mid) {
                if (copy_temp[index_a].compareTo(copy_temp[index_b]) >= 0) {
                    array[i] = copy_temp[index_b];
                    index_b = index_b + 1;
                } else {
                    array[i] = copy_temp[index_a];
                    index_a = index_a + 1;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        MergeSortFromBottomtoFront<Integer> mergeSortFromBottomtoFront = new MergeSortFromBottomtoFront<>();
        Integer[] array = new Integer[]{
                5, 9, 1, 2, 7, 6, 8
        };
        mergeSortFromBottomtoFront.sort2(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }


}
