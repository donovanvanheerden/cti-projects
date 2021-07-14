/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

import java.util.EmptyStackException;

/**
 *
 * @author DocMad
 */
public class myStack {
 
    //private final int SIZE;
    private Fruit[] stack;
    private int top;
    
    public myStack() {
//        SIZE = size;
        stack = new Fruit[0];
        top = -1;
    }
    
    public void push(Fruit fruit) {
        Fruit[] temp = stack.clone();
        stack = new Fruit[temp.length + 1];
        for (int i = 0; i < temp.length; i++) {
            stack[i] = temp[i];
        }
        stack[++top] = fruit;
    }
    
    public Fruit pop() {
        if (stack.length == 0) {
            throw new EmptyStackException();
        }
        
        int _top = stack.length - 1;
        Fruit fruit = stack[_top];

        Fruit[] temp = stack.clone();
        stack = new Fruit[temp.length - 1];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = temp[i];
        }
        
        top--;
        
        return fruit;
    }
    
    public Fruit peek() {
        return stack[top];
    }
    
    public boolean isEmpty() {
        return stack.length == 0;
    }
    
    @Override
    public String toString() {
        String fruits = "[";
        
        for(int i = 0; i < stack.length; i++) {
            
            if (i == stack.length - 1) {
                fruits += stack[i].getName();
            } else {
                fruits += stack[i].getName() + ", ";
            }
        }
        
        fruits += "]";
        
        return fruits;
    }
}
