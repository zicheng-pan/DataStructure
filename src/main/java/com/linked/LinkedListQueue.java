package com.linked;

import com.array.ArrayQueue;
import com.array.Queue;

/*
    这里改造链表，因为链表插入头时间夫再度是O(1)
    从最后一个元素删除节点的时间复杂度是O（n），所以需要引入尾指针
    引入尾指针后，向尾指针添加元素是O（1）操作但是删除尾指针，因为需要找到尾指针的前一个元素所以需要O（n）操作
    所以可以见，引入了尾指针之后，在头指针处，删除和添加第一个元素都是O（1）操作
    在尾指针处进行添加是O（1）操作，所以队列可以实现：
    1、在尾指针处进行添加元素
    2、在头指针出进行删除元素
 */
public class LinkedListQueue<E> implements Queue<E> {

    /*
        因为这里需要改造链表，所以不再直接使用我们之前的linkedList对象
     */

    private Node head, tail;
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

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        Node retNode = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return head.e;
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
        res.append("Queue front ");
        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
