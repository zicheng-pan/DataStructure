package com.linked;

public class LinkedListWithoutDummyHead<E> {

    private Node head;
    private int size;

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // add element to linkedList
    public void addFirst(E e) {
        /*
            Node node = new Node(e);
            node.next = head;
            head = node;
         */
        head = new Node(e, head); // 相当于上面三步骤
        size++;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, Illegal index");
        }

        if (index == 0)
            addFirst(e);
        else {
            Node prev = head;
            //找到index位置前一个位置就行添加元素
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            /*
                Node node = new Node(e);
                node.next = prev.next;
                prev.next = node;
            */
            prev.next = new Node(e, prev.next);
            size++;
        }

    }

    public void addLast(E e) {
        add(size, e);
    }
}
