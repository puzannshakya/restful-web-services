package com.assignment.postfixcalculator;

import java.util.EmptyStackException;

/**
 * This class implements a basic Linked Stack.
 *
 * @author Ed Grzyb
 */
public class LinkedStack<T> implements StackInterface<T> {

    private Node<T> top;

    /* Represents the top of the stack. */

    /**
     * The default constructor sets the top of stack (tos) to null.
     */
    public LinkedStack() {
        top = null;
    }

    /**
     * This method returns true if the stack is empty and false otherwise.
     *
     * @return true if the stack is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    /**
     * Pushes and item {@code item} onto the stack.
     * @param item of type T to push onto stack
     */
    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item, top);
        top = newNode;
    }

    /**
     * Pops the top element of the stack.
     *
     * @return the element popped off the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public T pop() throws EmptyStackException {
        Node<T> toPop = top;

        // If the stack is not empty, adjust to tos value and return true.
        if (!isEmpty()) {
            top = top.getNext();
            toPop.setNext(null);
            return toPop.getItem();
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Peeks at the value of the top of stack and returns that element to the
     * caller. There is no change to the stack itself.
     *
     * @return the element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     *
     */
    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getItem();
    }

    /**
     * Returns the item beneath the top of stack.
     *
     * @return the element beneath the top of the stack.
     * @throws IndexOutOfBoundsException if the stack has less than two
     * elements.
     */
    public T peek2() throws IndexOutOfBoundsException {
        if (top.getNext() == null) {
            throw new IndexOutOfBoundsException();
        }
        return top.getNext().getItem();
    }

    /**
     * Removes all entries from the stack.
     */
    @Override
    public void clear() {
        // Easiest way to clear the stack is to just
        // pop all of the entries.
        try {
            while (!isEmpty()) {
                pop();
            }
        } catch (EmptyStackException ex) {
            // Should never happen unless isEmpty() has an implementation
            // problem.
            System.err.println("LinkedStack.isEmpty() has an implementation problem.");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return "LinkedStack{" +
                "top=" + top +
                '}';
    }
}
