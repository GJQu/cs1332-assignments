import java.util.NoSuchElementException;

import javax.swing.text.html.MinimalHTMLWriter;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        if (backingArray.length - 1 == size) {
            T[] newBackingArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i < size + 1; i++) {
                newBackingArray[i] = backingArray[i];
            }
            backingArray = newBackingArray;
        }
        size++;
        // insert new data
        backingArray[size] = data;
        int currentIndex = size;
        while (currentIndex > 1 && backingArray[currentIndex].compareTo(backingArray[currentIndex / 2]) < 0){
            T temp = backingArray[currentIndex /2];
            backingArray[currentIndex / 2] = backingArray[currentIndex];
            backingArray[currentIndex] = temp;
            currentIndex = currentIndex / 2;
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T temp = backingArray[1];
        backingArray[1] = backingArray[size];
        size--;

        int currentIndex = 1;
        while (currentIndex * 2 <= size){
            int smallerChild = currentIndex * 2;
            if (smallerChild + 1 <= size && backingArray[smallerChild + 1].compareTo(backingArray[smallerChild]) < 0) {
                smallerChild = smallerChild + 1;
            }
            if (backingArray[currentIndex].compareTo(backingArray[smallerChild]) > 0) {
                T tempo = backingArray[currentIndex];
                backingArray[currentIndex] = backingArray[smallerChild];
                backingArray[smallerChild] = tempo;
            } else {
                break;
            }
            currentIndex = smallerChild;

        }
        backingArray[size + 1] = null;
        return temp;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}