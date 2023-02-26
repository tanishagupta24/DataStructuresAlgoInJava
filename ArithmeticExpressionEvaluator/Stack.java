package com.arithmetic;
import java.util.ArrayList;

public class Stack<E> {
    private ArrayList<E> stack;

    Stack() {
        stack = new ArrayList<>();
    }

    public void push(E item) {
        stack.add(item);
    }

    public E pop(){
        if(stack.isEmpty()) {
            return null;
        }
        E top = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return top;
    }

    public E top() {
        if(stack.isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }



}
