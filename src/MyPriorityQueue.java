//package src;
//
//public class MyPriorityQueue {
//    Patient[] heapArray;
//    public void insert() {}
//    public void removeMax() {}
//}
package src;

// T extends Comparable dedik ki hastaları birbiriyle kıyaslayabilelim (Aciliyet puanına göre)
public class MyPriorityQueue<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        // Generic array oluşturmak Java'da trick gerektirir, bu standart yoldur:
        this.heap = (T[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 1. EKLEME (INSERT): En sona ekle, sonra yukarı tırmandır
    public void add(T item) {
        if (size == capacity) {
            resize();
        }
        // Elemanı dizinin en sonuna koy
        heap[size] = item;
        size++;
        // Yukarı doğru düzelt (Heapify Up)
        bubbleUp(size - 1);
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        T[] newHeap = (T[]) new Comparable[newCapacity];
        // Eskileri kopyala
        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        capacity = newCapacity;
        System.out.println("Kuyruk kapasitesi artırıldı: " + capacity);
    }

    // 2. EN ÖNEMLİYİ ÇEKME (POLL/REMOVE MAX): Kökü al, sonuncuyu başa koy, aşağı it
    public T poll() {
        if (isEmpty()) return null;

        T result = heap[0]; // En tepedeki (en acil) eleman

        // Son elemanı tepeye taşı
        heap[0] = heap[size - 1];
        size--;

        // Aşağı doğru düzelt (Heapify Down)
        bubbleDown(0);

        return result;
    }

    // YUKARI TIRMANMA (Çocuk Babasından Büyükse Yer Değişir)
    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2;

        // Kök değilse VE Babamdan daha öncelikliysem (compareTo > 0)
        while (index > 0 && heap[index].compareTo(heap[parentIndex]) > 0) {
            swap(index, parentIndex);
            index = parentIndex; // Artık yeni yerim burası, tırmanmaya devam
            parentIndex = (index - 1) / 2;
        }
    }

    // AŞAĞI İTME (Baba Çocuklarından Küçükse, Büyük Olan Çocukla Yer Değişir)
    private void bubbleDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        // Sol çocuk var mı ve benden büyük mü?
        if (leftChild < size && heap[leftChild].compareTo(heap[largest]) > 0) {
            largest = leftChild;
        }

        // Sağ çocuk var mı ve şu anki en büyükten de büyük mü?
        if (rightChild < size && heap[rightChild].compareTo(heap[largest]) > 0) {
            largest = rightChild;
        }

        // Eğer en büyük ben değilsem, yer değiştir ve devam et
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

        // 1. Silinecek elemanın indisini bul (Linear Search)
        for (int i = 0; i < size; i++) {
            // T generic olduğu için cast etmemiz gerekebilir ya da
            // ERPatient içindeki patient'a ulaşmamız lazım.
            // Burada T'nin ERPatient olduğunu varsayarak işlem yapıyoruz:
            if (heap[i] instanceof ERPatient) {
                ERPatient erP = (ERPatient) heap[i];
                if (erP.patient.getPatientID() == id) {
                    indexToRemove = i;
                    break;
                }
            }
        }

        if (indexToRemove == -1) return false; // Bulunamadı

        // 2. Son elemanı, silinecek elemanın yerine koy
        heap[indexToRemove] = heap[size - 1];
        heap[size - 1] = null; // Bellek temizliği
        size--;

        // 3. Heap yapısını onar (Hem aşağı hem yukarı bakmak gerekebilir)
        // Basit çözüm: Önce aşağı it, olmazsa yukarı çıkar
        if (size > 0 && indexToRemove < size) {
            bubbleDown(indexToRemove);
            bubbleUp(indexToRemove);
        }

        return true;
    }
    // Heap dizisindeki elemanları yazdırır
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("    (Acil Servis Boş)");
            return;
        }

        // 1. Mevcut Heap dizisinin, sadece dolu olan kısmını kopyalıyoruz.
        // (Arrays sınıfını kullanabilmek için java.util.Arrays yazıyoruz)
        T[] tempArray = java.util.Arrays.copyOf(heap, size);

        // 2. Bu kopyayı sıralıyoruz.
        // Java varsayılan olarak Küçükten -> Büyüğe sıralar (1, 3, 5, 9 gibi).
        java.util.Arrays.sort(tempArray);

        // 3. Bizim için "Önceliği Yüksek" olan "Büyük" sayı olduğu için (10 puan > 1 puan),
        // listeyi SONDAN BAŞA doğru (Tersten) yazdırıyoruz.
        for (int i = tempArray.length - 1; i >= 0; i--) {
            System.out.println("    - " + tempArray[i].toString());
        }
    }
}
