package com.leetcode.test.array;


import java.util.Stack;

/*
    Stack usage
    Link: https://leetcode.com/problems/valid-parentheses/
 */
public class ValidationParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char item = s.charAt(i);

            if ("({[".contains(Character.toString(item))) {
                stack.push(item);
            } else if ((")}]".contains(Character.toString(item)))) {
                /*
                    stack 如果 "]" 只有一个这个，就会出现这样的情况出现error
                 */
                if (stack.isEmpty())
                    return false;
                char match = stack.pop();
                if (')' == item && '(' != match)
                    return false;
                if (']' == item && '[' != match)
                    return false;
                if ('}' == item && '{' != match)
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }

}
