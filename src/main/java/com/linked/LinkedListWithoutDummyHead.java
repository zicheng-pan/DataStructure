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

    /**
     * 单向链表的反转
     *
     * @return
     */
    public LinkedListWithoutDummyHead reverse3() {
        Node pre = null;
        Node cur = head;
        Node next = head.next;

        while (true) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
            if (next == null) {
                cur.next = pre;
                pre = cur;
                this.head = pre;
                return this;
            }
        }
    }

    public LinkedListWithoutDummyHead reverse4() {
        Node pre = null;
        Node cur = head;
        Node next = head.next;

        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        pre = cur;
        this.head = pre;
        return this;
    }

    public LinkedListWithoutDummyHead reverse() {
        Node pre = null;
        Node cur = head;
        Node next = head.next;

        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        pre = cur;
        this.head = pre;
        return this;
    }

    public LinkedListWithoutDummyHead reverse2() {
        Node pre = null;
        Node cur = head;

        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        this.head = pre;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }


    public static void main(String[] args) {
        LinkedListWithoutDummyHead<Integer> linkedListWithoutDummyHead = new LinkedListWithoutDummyHead<>();
        linkedListWithoutDummyHead.addFirst(1);
        linkedListWithoutDummyHead.addFirst(2);
        linkedListWithoutDummyHead.addFirst(3);
        linkedListWithoutDummyHead.addFirst(4);
        linkedListWithoutDummyHead.addFirst(5);
        System.out.println(linkedListWithoutDummyHead);
        System.out.println(linkedListWithoutDummyHead.reverse());
        System.out.println(linkedListWithoutDummyHead.reverse2());
    }
}
