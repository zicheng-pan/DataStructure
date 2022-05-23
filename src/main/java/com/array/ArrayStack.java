package com.array;

public class ArrayStack<E> implements Stack<E> {

    private Array<E> array = null;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }


    public ArrayStack() {
        array = new Array<>(10);
    }

    /*
        push操作可以看到和Array的addLast操作时间复杂度相同，即便有resize的操作均摊的时间复杂度是O(2)
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /*
        这里的时间复杂度也是O(1)和上面一样，时间复杂度均值
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[ ");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append(" ] top");
        return res.toString();
    }


    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.peek());
    }
}
