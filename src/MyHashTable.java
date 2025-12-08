//package src;
//
//public class MyHashTable {
//    Node[] table;
//    public void put() {}
//    public void get() {}
//}
package src;

public class MyHashTable<T> {
    // Hash tablosunun her odasında bir Linked List var (Çakışma çözümü için)
    private MyLinkedList<Entry<T>>[] table;
    private int capacity;

    // KÜÇÜK YARDIMCI SINIF (Anahtar - Değer çiftini tutar)
    // Bunu private yapabilirsin veya ayrı dosyada tutabilirsin
    public static class Entry<T> {
        int key; // ID
        T value; // Hasta Nesnesi

        public Entry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        // equals metodunu override edelim ki LinkedList remove çalışsın
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Entry<?> entry = (Entry<?>) obj;
            return key == entry.key; // Sadece Key (ID) eşitliği yeterli
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        // Generic array creation
        this.table = new MyLinkedList[capacity];

        // Her indekste boş bir liste oluşturalım
        for (int i = 0; i < capacity; i++) {
            table[i] = new MyLinkedList<>();
        }
    }

    // Hash Fonksiyonu (Basit Mod Alma)
    private int hash(int key) {
        return key % capacity;
    }

    // EKLEME (PUT)
    public void put(int key, T value) {
        int index = hash(key);

        // O indeksteki listeyi al
        MyLinkedList<Entry<T>> bucket = table[index];

        // Önce listede bu ID var mı kontrol et (Varsa güncelle)
        // NOT: Senin LinkedList'inde 'find' veya iterator yoksa manuel gezeceğiz.
        Node<Entry<T>> current = bucket.getHead();
        while(current != null) {
            if (current.data.key == key) {
                current.data.value = value; // Güncelle
                return;
            }
            current = current.next;
        }

        // Yoksa yeni ekle
        bucket.add(new Entry<>(key, value));
    }

    // GETİRME (GET)
    public T get(int key) {
        int index = hash(key);
        MyLinkedList<Entry<T>> bucket = table[index];

        Node<Entry<T>> current = bucket.getHead();
        while(current != null) {
            if (current.data.key == key) {
                return current.data.value; // Bulduk!
            }
            current = current.next;
        }

        return null; // Bulunamadı
    }

    // SİLME (REMOVE)
    public void remove(int key) {
        int index = hash(key);
        // Listeden silmek için dummy bir entry oluşturup remove çağırıyoruz
        // Bu çalışır çünkü Entry sınıfında equals metodunu sadece KEY'e bakacak şekilde yazdık.
        table[index].remove(new Entry<>(key, null));
    }
}
