package com;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this(10);
    }


    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == tail) resize(getCapacity() * 2);

        data[tail] = e;
        size++;
        /*
            注意维护tail
         */
        tail = (tail + 1) % data.length;
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        /*
            这里需要维护循环队列的context环境
         */
        data = newData;
        front = 0;
        tail = size;
    }

    public int getCapacity() {
        // 这里因为循环队列中判断是否是空是通过 front == tail来进行判断的，那么就需要错位来判断，当前队列是否是满， 所以用font+1 == tail来进行判断是不是满
        return data.length - 1;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
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
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue:");
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
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
