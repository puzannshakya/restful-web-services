package com.assignment.departmentstorequeues;

import java.util.LinkedList;

/**
 * An implementation of a Queue using the built in LinkedList from
 * the Java Collections API
 * 
 * @author Ed
 */
public class ListQueue<T> implements QueueInterface<T> {
    LinkedList<T> queue = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public T getFront() throws EmptyQueueException {
        return queue.getFirst();
    }
       

    @Override
    public T dequeue() throws EmptyQueueException {
        return queue.removeFirst();
    }

    @Override
    public void enqueue(T item) {
        queue.addLast(item);
    }

    @Override
    public void clear() {
        queue.clear();
    }


    public int getLength() throws EmptyQueueException {
        return queue.size();
    }
    
}
