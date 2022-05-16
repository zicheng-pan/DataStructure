package com;

import java.util.Arrays;

public class DequeueWithSize<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    // 使用size，而不浪费空间, 那么满的条件就是front == tail + 1 ||  size == data.length 这两个取一个就行
    private int size; // use size do not wast an empty space

    public DequeueWithSize(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public DequeueWithSize() {
        this(10);
    }

    public void addFront(E e) {
        if (size == data.length)
            resize(getCapacity() * 2);
        int prefront = front - 1 >= 0 ? front - 1 : front - 1 + data.length;
        data[prefront] = e;
        size++;
        front = prefront;
    }

    public E removeFront() {
        return this.dequeue();
    }

    public E removeLast() {
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(getCapacity() / 2);
        tail = tail - 1 >= 0 ? tail - 1 : tail - 1 + data.length;
        E e = data[tail];
        data[tail] = null;
        size--;
        return e;
    }

    public void addLast(E e) {
        this.enqueue(e);
    }

    @Override
    public void enqueue(E e) {
        if (size == data.length)
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        front = 0;
        tail = size;
        data = newData;
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Dequeue is empty, cannot dequeue");
        E result = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return result;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Dequeue [");
        for (int i = 0; i < data.length; i++) {
            res.append(data[i]);
            if (i != data.length - 1) {
                res.append(",");
            }
        }
        res.append("]");
        res.append(" end Size:" + size + " Array length: " + data.length);
        return res.toString();
    }
    public String toString1() {
        StringBuilder res = new StringBuilder();
        res.append("Dequeue:");
        res.append("front [");
        /*
            这里出现了 front == tail 并且size！=0的情况这里直接没有打印，认为dequeue中没有数据了
         */
//        for (int i = front; i != tail; i = (i+1) % data.length) {
//            res.append(data[i]);
//            if ((i + 1) % data.length != tail) {
//                res.append(",");
//            }
        /*
            所以使用size来进行打印判断
         */
        for (int i = 0; i < size; i++) {
            res.append(data[(i + front) % data.length]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }


    public static void main(String[] args) {
        DequeueWithSize<Integer> queue = new DequeueWithSize<>();
        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
        queue.addFront(111);
        System.out.println(queue);

        queue.addFront(222);
        System.out.println(queue);


        queue.removeLast();
        System.out.println(queue);

        queue.removeFront();
        System.out.println(queue);
    }
}

