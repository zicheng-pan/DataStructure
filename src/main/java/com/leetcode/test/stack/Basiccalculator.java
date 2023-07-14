package com.leetcode.test.stack;

import java.util.*;

/*
    https://leetcode.cn/problems/basic-calculator/
    s = "(1+(4+5+2)-3)+(6+8)"
    4+9/9+2*2
 */
public class Basiccalculator {

    Map<String, Integer> operation_level = new HashMap<>();

    Map<String, String> brackets = new HashMap<>();

    Stack<String> number_stack = new Stack<>();
    Stack<String> symbolStack = new Stack<>();

    public int calculate(String s) {
        initparam(operation_level, brackets);
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (" ".equals(c)) {
                continue;
            }
            if (brackets.containsKey(c)) {
                while (true) {
                    calculatenext();
                    int result = Integer.parseInt(number_stack.pop());
                    if (brackets.get(c).equals(String.valueOf(number_stack.peek()))) {
                        number_stack.pop();
                        number_stack.push(String.valueOf(result));
                        break;
                    }
                    number_stack.push(String.valueOf(result));
                }
            } else if (operation_level.containsKey(c)) {
                if (c.equals("-")){
                    number_stack.push("-"+number_stack.pop());
                    c="+";
                }
                if (!symbolStack.isEmpty()) {
                    while (operation_level.get(symbolStack.peek()) > operation_level.get(c)) {
                        calculatenext();
                    }
                }
                symbolStack.push(c);
            } else {
                number_stack.push(c);
            }
        }

        while (!symbolStack.isEmpty()) {
            calculatenext();
        }
        return Integer.parseInt(number_stack.peek());
    }

    private void calculatenext() {
        int num2 = Integer.parseInt(String.valueOf(number_stack.pop()));
        int num1 = Integer.parseInt(String.valueOf(number_stack.pop()));
        int result = docalculate(num1, num2, symbolStack.pop());
        number_stack.push(String.valueOf(result));
    }

    private int docalculate(int num1, int num2, String pop) {
        if (pop.equals("+"))
            return num1 + num2;
        else if (pop.equals("-"))
            return num1 - num2;
        else if (pop.equals("*"))
            return num1 * num2;
        else if (pop.equals("/"))
            return num1 / num2;
        System.out.println("error");
        return -1;
    }

    private void initparam(Map<String, Integer> operation_level, Map<String, String> brackets) {
        operation_level.put("+", 1);
        operation_level.put("-", 1);
        operation_level.put("*", 2);
        operation_level.put("/", 2);
        brackets.put(")", "(");
        brackets.put("}", "{");
        brackets.put("]", "[");
    }

    public static void main(String[] args) {
        Basiccalculator basiccalculator = new Basiccalculator();
//        String calculate = basiccalculator.calculate("4+3*9/9+2*2");
//        System.out.println(calculate);
        int calculate = basiccalculator.calculate(" 2-1 + 2");
        System.out.println(calculate);

    }

}
