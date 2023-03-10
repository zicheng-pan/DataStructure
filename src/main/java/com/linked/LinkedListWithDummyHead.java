package com.linked;

public class LinkedListWithDummyHead<E> {

    private Node dummyHead = new Node();
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
        add(0, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, Illegal index");
        }

        Node prev = dummyHead;
        //引入dummyhead的好处，不用判断链表是否存在头节点了，并且是从头节点的前一个节点开始计算，所以走index次，就是获取index位置的前一个节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;

    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illiegal index.");
        }
        //从第一个node节点开始计算，找到第index个节点，这样不用判断node.next != null 因为上面的判断确保了index位置铀元素
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur.e;
    }


    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illiegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }

    public boolean contain(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illiegal index.");
        }

        // 对于删除还是要找到前一个元素
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;

        size--;
        return retNode.e;
    }

    public E remvoeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }

    /**
     * 单向链表的反转
     *
     * @return
     */
    public Node reverse() {
        Node head = this.dummyHead.next;
        Node node = reverse_recurrence(head);
        this.dummyHead.next = node;
        return this.dummyHead;
    }

    public Node reverse_recurrence(Node node) {
        if (node == null || node.next == null)
            return node;
        Node head = reverse_recurrence(node.next);
        node.next.next = node;
        node.next = null;
        return head;
    }

    public static void main(String[] args) {
        LinkedListWithDummyHead list = new LinkedListWithDummyHead();
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
//            System.out.println(list);
        }
        list.add(2, 666);
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
}
