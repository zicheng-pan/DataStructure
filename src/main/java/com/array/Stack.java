package com.array;

public interface Stack<E> {

    void push(E e);

    E pop();

    int getSize();

    boolean isEmpty();

    E peek();
}
