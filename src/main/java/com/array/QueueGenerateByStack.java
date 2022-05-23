package com.array;

public class QueueGenerateByStack<E> implements Queue<E> {
    Stack<E> stack;
    Stack<E> tempStack;

    public QueueGenerateByStack(int capacity) {
        stack = new ArrayStack<>(capacity);
        tempStack = new ArrayStack<>(capacity);
    }

    public QueueGenerateByStack() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
        stack.push(e);
    }

    @Override
    public E dequeue() {
        if (tempStack.isEmpty()) {
            while (!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }
        }
        if (tempStack.isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return tempStack.pop();
    }

    @Override
    public E getFront() {
        return tempStack.peek();
    }

    @Override
    public int getSize() {
        return stack.getSize() + tempStack.getSize();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty() || tempStack.isEmpty();
    }

    public static void main(String[] args) {
        QueueGenerateByStack<Integer> queueGenerateByStack = new QueueGenerateByStack<>();
        for (int i = 0; i < 10; i++) {
            queueGenerateByStack.enqueue(i);
            if (i % 2 == 0) {
                int item = queueGenerateByStack.dequeue();
                System.out.println(item);
            }
        }
    }

}
