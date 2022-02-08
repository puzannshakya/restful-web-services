package com.assignment.departmentstorequeues;

/**
 * A concrete implementation of the ListInterface using an array
 * 
 * @author Ed Grzyb
 */
public class ArrayList<T> implements ListInterface<T> {
    private T[] items;       // The list of items stored.
    private static final int DEFAULT_CAPACITY = 100; // The default list capacity 
    private int itemCount;      // Number of actual elements in the list.

    /**
     * Constructs an empty list with capacity 100.
     */
    public ArrayList() {
        // Call the other constructor. Remember, this, is a reference to
        // this instance of the class.
        this(DEFAULT_CAPACITY);
    }

    /**
     * Allocates a list that can hold size entries.
     *
     * @param size an integer > 0.
     */
    public ArrayList(int size) throws IllegalArgumentException {
        itemCount = 0;

        if (size <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }

        // In general you should never suppress warnings but, in this case,
        // we know that cast will be valid since all entries are null.
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[size];
        items = temp;
    }

    /**
     * Tests if the list is empty.
     *
     * @return true if the list is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return (itemCount == 0);
    }

    /**
     * Returns the length of the list.
     *
     * @return a positive integer representing the length of the list.
     */
    @Override    
    public int getLength() {
        return itemCount;
    }

    /**
     * Inserts element entry at position position.
     *
     * @param index a number between 0 and list length inclusive.
     * @param entry the element to insert at {@code position} in the list.
     * @return true if successful; otherwise, false.
     */
    @Override
    public boolean insert(int index, T entry) {        
        // See if we have space.
        if (((itemCount) >= items.length) || (index < 0) || (index > (itemCount))) {
            return false;
        }

        // Shift all elements  right one position and update itemCount.
        for (int i = itemCount; i >= index; i--) {
            items[i + 1] = items[i];
        }
        itemCount++;

        // Insert the element at the given index;
        items[index] = entry;
        return true;
    }

    /**
     * Removes the element at index index.
     *
     * @param index a number between 0 and list length - 1 inclusive.
     * @return true if successful; otherwise, false is returned.
     */
    @Override
    public boolean remove(int index) {
        // Nothing to remove.
        if (index < 0 || index >= itemCount) {
            return false;
        }

        // Shift everything left one.
        for (int i = index; i <= itemCount; i++) {
            items[i] = items[i + 1];
        }
        itemCount--;
        return true;
    }

    /**
     * Removes all items from the list.
     */
    @Override
    public void clear() {
        itemCount = 0;
    }

    /**
     * Determines if {@code entry} is contained in the list.
     *
     * @param entry the entry to search for.
     * @return true if the entry is in the list; otherwise, false.
     */
    @Override
    public boolean contains(T entry) {
        for (T item : items) {
            if (item != null && item.equals(entry)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the item at entry index, provided the index is valid.
     *
     * @param index a number between 0 and list-1 length inclusive.
     * @return the item found at {@code index}.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    @Override    
    public T getEntry(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < itemCount) {
            return items[index];
        } else {
            throw new IndexOutOfBoundsException("Given index is out of bounds.");
        }
    }

    /**
     * Replaces the item at index index with entry entry. The replaced
     * entry is returned.
     *
     * @param index a number between 0 and list length - 1 inclusive.
     * @param entry to repelace the entry at {@code position} with.
     * @return the item that was replaced.
     */
    @Override
    public T replace(int index, T entry) throws IndexOutOfBoundsException {
        T tmp;
        if (index >= 0 && index < itemCount) {
            tmp = items[index];
            items[index] = entry;
            return tmp;
        } else {
            throw new IndexOutOfBoundsException("Given index is out of bounds.");
        }
    }

    /**
     * Returns the array form of the list data.
     *
     * @return an array of list data ordered the same as the list.
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[itemCount];

        for (int i=0; i<itemCount; i++) {              
            result[i] = items[i];            
        }

        return result;
    }

    /**
     * Returns a string representation of the list.
     *
     * @return A string representation of the list.
     */
    @Override
    public String toString() {
        String listString = "";

        // Check to see if the list is empty.
        if (itemCount == 0) {
            listString = "Empty List.";
        }

        // Print out all of the elements of the list.
        for (int index = 0; index < itemCount; index++) {
            listString += "[" + index + "]: " + items[index] + "\n";
        }

        return listString;
    }

}
