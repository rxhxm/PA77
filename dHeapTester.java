import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.Test;

public class dHeapTester {


    @Test
    public void testNoArgumentConstructor() {
        dHeap<Integer> heap = new dHeap<>();
        assertNotNull("Heap should not be null", heap);
        assertEquals("Default heap should be max heap", true, heap.isMaxHeap());
        assertEquals("Default heap size should be 0", 0, heap.size());
    }

    private void assertNotNull(String heapShouldNotBeNull, dHeap<Integer> heap) {
    }

    @Test
    public void testSingleArgumentConstructorValidHeapSize() {
        dHeap<Integer> heap = new dHeap<>(15);
        assertNotNull("Heap should not be null", heap);
        assertEquals("Heap should be max heap", true, heap.isMaxHeap());
        assertEquals("Heap size should be 0", 0, heap.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSingleArgumentConstructorNegativeHeapSize() {
        new dHeap<Integer>(-1);
    }

    @Test
    public void testThreeArgumentConstructorMaxHeap() {
        dHeap<Integer> maxHeap = new dHeap<>(3, 20, true);
        assertNotNull("Heap should not be null", maxHeap);
        assertEquals("Heap should be max heap", true, maxHeap.isMaxHeap());
        assertEquals("Heap size should be 0", 0, maxHeap.size());
    }

    @Test
    public void testThreeArgumentConstructorMinHeap() {
        dHeap<Integer> minHeap = new dHeap<>(4, 20, false);
        assertNotNull("Heap should not be null", minHeap);
        assertEquals("Heap should be min heap", false, minHeap.isMaxHeap());
        assertEquals("Heap size should be 0", 0, minHeap.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThreeArgumentConstructorInvalidD() {
        new dHeap<Integer>(0, 20, true);
    }










    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        dHeap<Integer> heap = new dHeap<>();
        heap.add(null);
    }

    @Test
    public void testAddSingleElement() {
        dHeap<Integer> heap = new dHeap<>();
        heap.add(10);
        assertEquals("Heap size after adding one element", 1, heap.size());
        assertEquals("Element added is the root of the heap", Integer.valueOf(10), heap.element());
    }

    @Test
    public void testAddMultipleElements() {
        dHeap<Integer> heap = new dHeap<>();
        heap.add(20);
        heap.add(10);
        heap.add(30);
        assertEquals("Heap size after adding elements", 3, heap.size());
        assertTrue("Root of the heap should be the maximum element", heap.element().equals(30));
    }

    @Test
    public void testResizeAndHeapProperty() {
        dHeap<Integer> heap = new dHeap<>(2, 2, true); // Initial capacity of 2 to force resize
        heap.add(10);
        heap.add(20);
        heap.add(5); // This should cause a resize
        assertEquals("Heap size after resize", 3, heap.size());
        assertTrue("Root of the heap should still be the maximum element after resize", heap.element().equals(20));
    }

    @Test
    public void testAddElementsMinHeapOrder() {
        dHeap<Integer> minHeap = new dHeap<>(2, 10, false); // Min-heap
        minHeap.add(20);
        minHeap.add(10);
        minHeap.add(30);
        assertEquals("Heap size after adding elements", 3, minHeap.size());
        assertTrue("Root of the min-heap should be the minimum element", minHeap.element().equals(10));

        // Removing elements to test order
        assertEquals("Removing root (smallest element)", Integer.valueOf(10), minHeap.remove());
        assertEquals("Next root (next smallest element)", Integer.valueOf(20), minHeap.remove());
        assertEquals("Last element (largest element)", Integer.valueOf(30), minHeap.remove());
    }











    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmptyHeap() {
        dHeap<Integer> heap = new dHeap<>();
        heap.remove();
    }

    @Test
    public void testRemoveSingleElement() {
        dHeap<Integer> heap = new dHeap<>();
        heap.add(10);
        assertEquals("Removing the only element", Integer.valueOf(10), heap.remove());
        assertEquals("Heap should be empty", 0, heap.size());
    }

    @Test
    public void testRemoveMultipleElementsMaxHeap() {
        dHeap<Integer> maxHeap = new dHeap<>();
        maxHeap.add(20);
        maxHeap.add(30);
        maxHeap.add(10);
        assertEquals("First remove should get max element", Integer.valueOf(30), maxHeap.remove());
        assertEquals("Second remove should get next max element", Integer.valueOf(20), maxHeap.remove());
    }

    @Test
    public void testRemoveMultipleElementsMinHeap() {
        dHeap<Integer> minHeap = new dHeap<>(2, 10, false);
        minHeap.add(20);
        minHeap.add(30);
        minHeap.add(10);
        assertEquals("First remove should get min element", Integer.valueOf(10), minHeap.remove());
        assertEquals("Second remove should get next min element", Integer.valueOf(20), minHeap.remove());
    }

    @Test
    public void testRepeatedAddAndRemove() {
        dHeap<Integer> heap = new dHeap<>();
        heap.add(10);
        heap.add(20);
        assertEquals("Remove after adds", Integer.valueOf(20), heap.remove());
        heap.add(15);
        assertEquals("Remove after more adds", Integer.valueOf(15), heap.remove());
    }






        @Test
        public void testClearEmptyHeap() {
            dHeap<Integer> heap = new dHeap<>();
            heap.clear();
            assertEquals("Heap should be empty after clearing", 0, heap.size());
        }

        @Test
        public void testClearHeapWithElements() {
            dHeap<Integer> heap = new dHeap<>();
            heap.add(10);
            heap.add(20);
            heap.clear();
            assertEquals("Heap should be empty after clearing", 0, heap.size());
        }

        @Test
        public void testAddAfterClear() {
            dHeap<Integer> heap = new dHeap<>();
            heap.add(10);
            heap.clear();
            heap.add(20);
            assertEquals("Heap should have 1 element after adding post-clear", 1, heap.size());
            assertEquals("Element added after clear should be in heap", Integer.valueOf(20), heap.element());
        }








    @Test(expected = NoSuchElementException.class)
    public void testElementEmptyHeap() {
        dHeap<Integer> heap = new dHeap<>();
        heap.element();
    }

    @Test
    public void testElementSingleElement() {
        dHeap<Integer> heap = new dHeap<>();
        heap.add(10);
        assertEquals("Element should return the root", Integer.valueOf(10), heap.element());
    }

    @Test
    public void testElementMultipleElements() {
        dHeap<Integer> heap = new dHeap<>();
        heap.add(20);
        heap.add(10);
        assertEquals("Element should return the root", Integer.valueOf(20), heap.element());
    }

}