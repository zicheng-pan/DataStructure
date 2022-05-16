package com;

public class DequeueWithoutSize<E> implements Queue<E> {

    private E[] data;

    //这里不使用size的情况下，可以通过 tail - front来计算size的大小，然后替换之前代码中全部的size 理论上就可以了
    //可以完全使用 tail + 1 == front来判断是否为满，通过浪费一个空间来实现
    //用tail == front来判断空
    private int front, tail;

    public DequeueWithoutSize(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public DequeueWithoutSize() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
