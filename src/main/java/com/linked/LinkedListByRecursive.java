package com.linked;

public class LinkedListByRecursive<E> {

    private int size;
    private Node head;

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
            if (e != null)
                return e.toString();
            else
                return "null";
        }
    }

    public int getSize() {
        return size;
    }

    public void add(int index, E e) {
        head = add(head, index, e);
    }

    private Node add(Node head, int index, E e) {
        if (index == 0) {
            size++;
            return new Node(e, head);
        }
        head.next = add(head.next, index - 1, e);
        return head;
    }

    public void remove(int index) {
        head = remove(head, index);
    }

    public Node remove(Node head, int index) {
        if (index == 0) {
            size--;
            return head.next;
        }
        head.next = remove(head.next, index - 1);
        return head;
    }

    public Node remove(Node head, E value) {
        if (head == null) {
            size--;
            return null;
        }
        head.next = remove(head.next, value);
        return head.equals(value) ? head.next : head;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = head.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }


    public static void main(String[] args) {
        LinkedListByRecursive<Integer> list = new LinkedListByRecursive();
        for (int i = 0; i < 5; i++) {
            list.add(0, i);
            System.out.println(list);
        }
        list.add(2, 666);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
    }
}
