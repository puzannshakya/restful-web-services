package com.assignment.postfixcalculator;

/**
 * A simple Stack interface
 * 
 * @author Ed Grzyb
 */
public interface StackInterface<T> {
    public boolean isEmpty();
    public void push(T entry);
    public T pop();
    public T peek();
    public void clear();
}
