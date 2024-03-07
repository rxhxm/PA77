/*
 * Name: Roham Mehrabi
 * PID:  A17025640
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Roham Mehrabi
 * @since March 6, 2024
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min

    public boolean isMaxHeap() {
        return this.isMaxHeap;
    }

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this.heap = (T[]) new Comparable[10];
        this.d = 2;
        this.nelems = 0;
        this.isMaxHeap = true;

    }


    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.heap = (T[]) new Comparable[heapSize];
        this.d = 2;
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        this.heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
    }

    @Override
    public int size() {
        return nelems;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (nelems ==0){
            throw new NoSuchElementException("Heap's empty");

        }

            T removedItem = heap[0];
            heap[0] = heap[nelems - 1];
            heap[nelems - 1] = null;
            nelems = nelems - 1;
            if (nelems > 0) {
                trickleDown(0);
            }
            return removedItem;
        }

    

    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException("Data is null");
        }
        if (nelems == heap.length){
            resize();
        }
        heap[nelems] = item;
        nelems++;
        bubbleUp(nelems-1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        for (int i = 0; i< nelems; i++){
            heap[i] = null;
        }
        nelems = 0;
    }

    @Override
    public T element() throws NoSuchElementException {
        if (nelems == 0){
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    private int parent(int index) {
        return (index - 1) /d ;
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / d;
            if (compare(heap[index], heap[parentIndex])) {
                T temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void trickleDown(int index) {
        boolean done = false;
        while (!done && (d * index + 1) < nelems) {
            int childIndex = d * index + 1;
            T tmp = heap[index];
    
            for (int i = 2; i <= d; i++) {
                int nextChildIdx = d * index + i;
                if (nextChildIdx < nelems) {
                    boolean isNextChildBetter = compare(heap[nextChildIdx], heap[childIndex]);
                    if (isNextChildBetter) {
                        childIndex = nextChildIdx;
                    }
                }
            }
    
            if (compare(tmp, heap[childIndex])) {
                heap[index] = heap[childIndex];
                heap[childIndex] = tmp;
                index = childIndex;
            } else {
                done = true;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = heap.length * 2;
        T[] newHeap = (T[]) new Comparable[newSize];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;

    }

    private boolean compare(T x, T y) {
        return isMaxHeap ? x.compareTo(y) > 0 : x.compareTo(y) < 0;
    }

}
