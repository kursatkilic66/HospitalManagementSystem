//package src;
//
//public class MyHashTable {
//    Node[] table;
//    public void put() {}
//    public void get() {}
//}
package src;

public class MyHashTable<T> {

    private MyLinkedList<Entry<T>>[] table;
    private int capacity;


    public static class Entry<T> {
        int key;
        T value;

        public Entry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Entry<?> entry = (Entry<?>) obj;
            return key == entry.key;
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new MyLinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new MyLinkedList<>();
        }
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void put(int key, T value) {
        int index = hash(key);

        MyLinkedList<Entry<T>> bucket = table[index];

        Node<Entry<T>> current = bucket.getHead();
        while(current != null) {
            if (current.data.key == key) {
                current.data.value = value;
                return;
            }
            current = current.next;
        }

        bucket.add(new Entry<>(key, value));
    }

    public T get(int key) {
        int index = hash(key);
        MyLinkedList<Entry<T>> bucket = table[index];

        Node<Entry<T>> current = bucket.getHead();
        while(current != null) {
            if (current.data.key == key) {
                return current.data.value;
            }
            current = current.next;
        }

        return null;
    }

    public void remove(int key) {
        int index = hash(key);
        table[index].remove(new Entry<>(key, null));
    }
}
