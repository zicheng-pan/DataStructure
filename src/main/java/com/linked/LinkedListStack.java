package com.linked;

import com.array.Stack;
/*

    @comparewith ArrayStack
    ArrayStack 中的resize操作会有copy数组，这里耗费时间会对，但是LikedStack中如果add过频繁会出现大量的new 对象的操作也同样是耗时间的问题
    所以和JVM和操作系统相关，不一定是绝对的
    但是ArrayStack和LinkedStack同样都是O(1)级别的操作所以计算下来不会有数量级别的差异
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedListWithDummyHead<E> list = new LinkedListWithDummyHead();

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.remvoeFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}
