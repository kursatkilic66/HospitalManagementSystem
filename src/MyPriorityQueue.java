//package src;
//
//public class MyPriorityQueue {
//    Patient[] heapArray;
//    public void insert() {}
//    public void removeMax() {}
//}
package src;

public class MyPriorityQueue<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = (T[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T item) {
        if (size == capacity) {
            resize();
        }
        heap[size] = item;
        size++;
        bubbleUp(size - 1);
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        T[] newHeap = (T[]) new Comparable[newCapacity];
        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        capacity = newCapacity;
        System.out.println("Queue Capacity Has Increased!: " + capacity);
    }

    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && heap[index].compareTo(heap[parentIndex]) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void bubbleDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        if (leftChild < size && heap[leftChild].compareTo(heap[largest]) > 0) {
            largest = leftChild;
        }

        if (rightChild < size && heap[rightChild].compareTo(heap[largest]) > 0) {
            largest = rightChild;
        }

        if (largest != index) {
            swap(index, largest);
            bubbleDown(largest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean removeById(int id) {
        int indexToRemove = -1;

        for (int i = 0; i < size; i++) {
            if (heap[i] instanceof ERPatient) {
                ERPatient erP = (ERPatient) heap[i];
                if (erP.patient.getPatientID() == id) {
                    indexToRemove = i;
                    break;
                }
            }
        }

        if (indexToRemove == -1) return false;


        heap[indexToRemove] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0 && indexToRemove < size) {
            bubbleDown(indexToRemove);
            bubbleUp(indexToRemove);
        }

        return true;
    }
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("    (EMERGENCY SERVICE EMPTY!)");
            return;
        }
        T[] tempArray = java.util.Arrays.copyOf(heap, size);
        java.util.Arrays.sort(tempArray);
        for (int i = tempArray.length - 1; i >= 0; i--) {
            System.out.println("    - " + tempArray[i].toString());
        }
    }
}
