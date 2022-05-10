package com;

import java.util.Arrays;

public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array size = %d , capacity = %d", size, getCapacity()));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Get failed. Index is illegal.");
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return true;
        }
        return false;
    }

    public void add(int index, E e) {
//        if (size == data.length) throw new IllegalArgumentException("AddLast failed. Array is full.");
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 && index <" + data.length + ".");

        /*
            这里需要边界得判断 i - 1 需要 >0
         */
//        for (int i = size; i >= index; i--) {
//            if (i - 1 >= 0) data[i] = data[i - 1];
//        }
        if (size == getCapacity()) {
            resize(2 * getCapacity());
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }


    public void addFirst(E e) {
        add(0, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. Index is illegal.");
        }
        E temp = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        // 非必要，为了优化JVM垃圾回收
        data[size] = null;
        // 进行缩容,并且避免复杂度震荡得问题
        if (size == data.length / 4) resize(data.length / 2);
        return temp;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        remove(index);
    }

    public void removeAllElement(E e) {
        while (find(e) != -1) {
            remove(find(e));
        }
    }

    private void resize(int newcapacity) {
        E[] newdata = (E[]) new Object[newcapacity];
        for (int i = 0; i < size; i++) {
            newdata[i] = data[i];
        }
        data = newdata;
    }

    public static void main(String[] args) {
        Array array = new Array();
        array.addFirst(1);
        array.addFirst(2);
        array.addFirst(3);
        array.addFirst(4);
        array.addLast(5);
        array.add(1, 100);
        array.add(5, 100);
        array.add(5, 100);
        array.add(5, 100);
        array.add(5, 100);
        System.out.println(array);
        array.add(5, 100);
        System.out.println(array);
        array.removeAllElement(100);
        System.out.println(array);
//        System.out.println(array.get(1));
//        array.set(1, 200);
//        System.out.println(array.get(1));
//        array.remove(1);
//        array.remove(1);
//        array.remove(1);
//        System.out.println(array);
    }
}
