package com.stack;

import java.util.*;

public class BracketValidator {

    protected int x;

    private static final Map<Character, Character> charMap = new HashMap<>();

    static {
        charMap.put(')', '(');
        charMap.put(']', '[');
        charMap.put('}', '{');

    }

    public static boolean validate(List<Character> symbols) {

        if(symbols.size() % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();

        for(char symbol : symbols) {
            if(charMap.values().contains(symbol)) {
                stack.push(symbol);
            }
            else if(!stack.isEmpty() && charMap.containsKey(symbol) && charMap.get(symbol).equals(stack.peek())) {
                stack.pop();
            }
            else {
                return false; // short circuit here
            }
        }

        return stack.isEmpty();
    }

    public static void main(String args[]) {
        List<Character> charList = Arrays.asList('{', '(', ')', '{', '(', ')', '[', ']', '}', '}');
        boolean validate = validate(charList);
        System.out.println(validate);

    }
}
