package com.leetcode.test.stack;

import java.util.Stack;

/*
    leetcode:https://leetcode.cn/problems/min-stack/
 */
public class MinStack {

    Stack<Integer> stack = new Stack();
    Stack<Integer> ministack = new Stack();

    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);
        if (ministack.isEmpty() || val <= ministack.peek())
            ministack.push(val);
    }

    public void pop() {
        if (stack.peek().equals(ministack.peek()))
            ministack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return ministack.peek();
    }


}

