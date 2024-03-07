/*
 * Name: Roham Mehrabi
 * PID:  A17025640
 */

import java.util.NoSuchElementException;

/**
 * Priority Queue Implementation using dHeap.
 *
 * @author Roham Mehrabi
 * @since Mar 5, 2024
 *
 * @param <T> the type of elements held in this collection
 */

public class MyPriorityQueue<T extends Comparable<? super T>> {

    private dHeap<T> pQueue;

    /**
     * Constructor that creates a new priority queue
     *
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        this.pQueue = new dHeap<>(initialSize);
    }

 

    /**
     * Retrieve and remove the head of this Priority Queue (smallest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (smallest element), or null if queue is empty.
     */
    public T poll() {
        try {
            return pQueue.remove();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        pQueue.clear();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     *
     * @return the head of the queue, null if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return pQueue.element();
    }

    /**
     * Return true is the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return pQueue.size() == 0;
    }


    public boolean offer(T element) {
        pQueue.add(element); 
        return true; 
    }

    
}
