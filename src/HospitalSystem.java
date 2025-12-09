//package src;
//
//public class HospitalSystem {
//    MyHashTable patientTable;
//    MyBST nameSearchTree;
//    MyPriorityQueue<ERPatient> emergencyRoom = new MyPriorityQueue<>(10);
//    MyStack undoStack;
//    MyGeneralTree hospitalStructure;
//    public void registerPatient(Patient p) {
//
//    }
//
//    // Kuyruğu ERPatient tutacak şekilde tanımla
//
//    public void admitToER(Patient p) {
//        // Hastayı paketleyip kuyruğa at
//        emergencyRoom.add(new ERPatient(p));
//    }
//    public void processUndo() {
//
//    }
//}
package src;

import java.util.Random;

public class HospitalSystem {
    // Veri Yapıları
    public MyPriorityQueue<ERPatient> emergencyQueue; // Acil Hastalar (Heap)
    public MyQueue<Patient> normalQueue;              // Normal Hastalar (FIFO)
    public MyStack<Integer> undoStack;                // Geri alma için ID tutacak
    public MyGeneralTree<String> hospitalStructure;   // Hastane Hiyerarşisi
    public MyBST<Patient> patientArchive;             // İsimle arama yapmak için (Bonus)

    // ID Üreteci
    private Random random;
    private long seed;

    public HospitalSystem(long seed) {
        this.seed = seed;
        this.random = new Random(seed); // Seed'i buraya set ediyoruz

        // Kapasiteleri belirleyelim
        this.emergencyQueue = new MyPriorityQueue<>(100);
        this.normalQueue = new MyQueue<>();
        this.undoStack = new MyStack<>();
        this.patientArchive = new MyBST<>();

        // Ağacı Başlat
        initializeTree();
    }

    // --- AĞAÇ YAPISINI OLUŞTURMA ---
    private void initializeTree() {
        // NOT: MGTNode constructor'ında children = new MyLinkedList<>()
        // eklediğinden emin olmalısın.
        MGTNode<String> root = new MGTNode<>("Bashekimlik");
        hospitalStructure = new MyGeneralTree<>(root);

        MGTNode<String> adminNode = new MGTNode<>("Idari Birimler");
        MGTNode<String> medicalNode = new MGTNode<>("Tibbi Birimler");

        // Root'a çocukları ekle
        hospitalStructure.addNode(root, adminNode.data);
        hospitalStructure.addNode(root, medicalNode.data);

        // İdari birimlere ekle
        hospitalStructure.addNode(adminNode, "Insan Kaynaklari");
        hospitalStructure.addNode(adminNode, "Muhasebe");

        // Tıbbi birimlere ekle
        hospitalStructure.addNode(medicalNode, "Acil Servis");
        hospitalStructure.addNode(medicalNode, "Dahiliye");
        hospitalStructure.addNode(medicalNode, "KBB");
    }

    // --- HASTA KAYIT (GÜNCELLENDİ: SEVERITY EKLENDİ) ---
    public void registerPatient(String name, int age, boolean isEmergency, int severity) {
        // 1. Unique ID Üretimi (Seed sayesinde her çalıştırmada aynı sırayı verir)
        // 9 haneli sayılar üretelim: 100.000.000 ile 999.999.999 arası
        int id = 100000000 + random.nextInt(900000000);

        // 2. Aciliyet Seviyesi
        // Eğer acilse parametre olarak gelen 'severity' (1-10), değilse standart 1.
        int priority = isEmergency ? severity : 1;

        // 3. Hasta Nesnesi Oluştur
        Patient newPatient = new Patient(null, name, priority, age, id);

        // 4. İlgili Kuyruğa Ekle
        if (isEmergency) {
            // Priority Queue 'ERPatient' tutuyordu, sarmalayıp ekliyoruz
            emergencyQueue.add(new ERPatient(newPatient));
            System.out.println("⚠️ [ACİL] Hasta Kaydedildi: " + name + " | ID: " + id + " | Derece: " + severity);
        } else {
            normalQueue.enqueue(newPatient);
            System.out.println("✅ [NORMAL] Hasta Kaydedildi: " + name + " | ID: " + id);
        }

        // 5. Arşiv Ağacına Ekle
        patientArchive.insert(newPatient);

        // 6. Undo Stack'e ID'yi at
        undoStack.push(id);
    }

    // --- UNDO (GÜNCELLENDİ: GERÇEK SİLME) ---
    public void processUndo() {
        if (undoStack.isEmpty()) {
            System.out.println("❌ Geri alınacak işlem yok!");
            return;
        }

        // Stack'ten son eklenen ID'yi çek
        Integer lastId = undoStack.pop();
        boolean removed = false;

        // 1. ADIM: ACİL KUYRUĞUNDAN SİLMEYİ DENE
        // (MyPriorityQueue sınıfına removeById eklediysen bu çalışır)
        if (emergencyQueue.removeById(lastId)) {
            System.out.println("🔙 UNDO BAŞARILI: Hasta (ID: " + lastId + ") ACİL servis kuyruğundan silindi.");
            removed = true;
        }

        // 2. ADIM: EĞER ACİLDE YOKSA NORMAL KUYRUĞA BAK
        else {
            // Normal kuyruk LinkedList olduğu için 'equals' metodunu kullanır.
            // Silebilmek için sadece ID'si aynı olan sahte bir hasta objesi oluşturuyoruz.
            Patient dummyForRemoval = new Patient();
            dummyForRemoval.setPatientID(lastId);

            // Patient sınıfına 'equals' metodunu eklediysen ID kıyaslayıp siler.
            normalQueue.remove(dummyForRemoval);

            System.out.println("🔙 UNDO BAŞARILI: Hasta (ID: " + lastId + ") Normal poliklinik sırasından çıkarıldı.");

            // Not: MyLinkedList.remove metodu void olduğu için kesin silindi mi bilemiyoruz
            // ama listede varsa silinmiştir.
        }
    }

    // --- DOKTOR SIRASI GÖRÜNTÜLEME ---
//    public void showAllQueues() {
//        System.out.println("\n=== GÜNCEL HASTA KUYRUKLARI ===");
//
//        System.out.println("--- 🚑 ACİL SERVİS (Öncelik Puanına Göre) ---");
//        if (emergencyQueue.isEmpty()) {
//            System.out.println("  (Sırada bekleyen hasta yok)");
//        } else {
//            // Heap'in içini bozmadan göstermek zor olduğu için sadece durum bilgisi
//            System.out.println("  Sırada bekleyen hastalar mevcut. (En yüksek öncelikli önde)");
//        }
//
//        System.out.println("\n--- 🏥 NORMAL POLİKLİNİK (Sırayla) ---");
//        if (normalQueue.isEmpty()) {
//            System.out.println("  (Sırada bekleyen hasta yok)");
//        } else {
//            // Eğer MyQueue içinde printLine() metodun çalışıyorsa burayı açabilirsin:
//            // System.out.println(normalQueue.printLine());
//            System.out.println("  Sırada bekleyen hastalar mevcut.");
//        }
//        System.out.println("===========================================");
//    }
    public void showAllQueues() {
        System.out.println("\n=== GÜNCEL HASTA KUYRUKLARI ===");

        System.out.println("--- 🚑 ACİL SERVİS (Öncelik Puanına Göre) ---");
        // Artık sadece mesaj değil, listeyi yazdırıyoruz:
        emergencyQueue.printQueue();

        System.out.println("\n--- 🏥 NORMAL POLİKLİNİK (Sırayla) ---");
        // Normal kuyruğu yazdırıyoruz:
        normalQueue.printQueue();

        System.out.println("===========================================");
    }

    public void showHierarchy() {
        System.out.println("\n--- 🏢 HASTANE HİYERARŞİSİ (General Tree) ---");
        // Root'tan başlayarak yazdır
        hospitalStructure.printTree(hospitalStructure.root, "");
    }
}