package src;

import java.util.Random;

public class HospitalSystem {

    public MyPriorityQueue<ERPatient> emergencyQueue;
    public MyQueue<Patient> normalQueue; // Genel Kayıt Defteri
    public MyStack<Integer> undoStack;
    public MyGeneralTree<String> hospitalStructure;
    public MyBST<Patient> patientArchive;

    // --- YENİ EKLENEN HASH TABLOLARI ---
    public MyHashTable<Doctor> doctorTable;
    public MyHashTable<Patient> patientTable;

    public Department kbbDept;
    public Department dahiliyeDept;
    public Department psikolojiDept;

    private Random random;
    private long seed;

    public HospitalSystem(long seed) {
        this.seed = seed;
        this.random = new Random(seed);

        this.emergencyQueue = new MyPriorityQueue<>(100);
        this.normalQueue = new MyQueue<>();
        this.undoStack = new MyStack<>();
        this.patientArchive = new MyBST<>();

        // Tabloları başlatıyoruz
        this.doctorTable = new MyHashTable<>(50);
        this.patientTable = new MyHashTable<>(500);

        // Konsolda başlangıçta görmek için süsleme
        System.out.println("Sistem başlatılıyor... Doktor ID'leri oluşturuluyor...");
        System.out.println("-----------------------------------------------------");

        initializeDepartmentsAndDoctors();

        System.out.println("-----------------------------------------------------");

        initializeTree();
    }

    private void initializeDepartmentsAndDoctors() {
        kbbDept = new Department("Kulak Burun Bogaz", 5);
        dahiliyeDept = new Department("Dahiliye", 5);
        psikolojiDept = new Department("Psikoloji", 5);

        // --- KBB Doktorları ---
        Doctor d1 = new Doctor(generateID(), "Yusuf Emre Güntekin", "KBB");
        kbbDept.addDoctor(d1);
        doctorTable.put(d1.doctorID, d1);
        // TEST İÇİN ID YAZDIRMA:
        System.out.println("👨‍⚕️ Doktor: " + d1.name + " | ID: " + d1.doctorID);

        Doctor d2 = new Doctor(generateID(), "Mehmet Kaplan", "KBB");
        kbbDept.addDoctor(d2);
        doctorTable.put(d2.doctorID, d2);
        System.out.println("👨‍⚕️ Doktor: " + d2.name + " | ID: " + d2.doctorID);

        // --- Dahiliye Doktorları ---
        Doctor d3 = new Doctor(generateID(), "Türker Emre Kuru", "Dahiliye");
        dahiliyeDept.addDoctor(d3);
        doctorTable.put(d3.doctorID, d3);
        System.out.println("👨‍⚕️ Doktor: " + d3.name + " | ID: " + d3.doctorID);

        Doctor d4 = new Doctor(generateID(), "Arda Yaşar", "Dahiliye");
        dahiliyeDept.addDoctor(d4);
        doctorTable.put(d4.doctorID, d4);
        System.out.println("👨‍⚕️ Doktor: " + d4.name + " | ID: " + d4.doctorID);

        // --- Psikoloji Doktorları ---
        Doctor d5 = new Doctor(generateID(), "Mert Kaçmaz", "Psikoloji");
        psikolojiDept.addDoctor(d5);
        doctorTable.put(d5.doctorID, d5);
        System.out.println("👨‍⚕️ Doktor: " + d5.name + " | ID: " + d5.doctorID);
    }

    private void initializeTree() {
        MGTNode<String> root = new MGTNode<>("Bashekimlik");
        hospitalStructure = new MyGeneralTree<>(root);

        MGTNode<String> adminNode = new MGTNode<>("Idari Birimler");
        MGTNode<String> medicalNode = new MGTNode<>("Tibbi Birimler");

        hospitalStructure.addNode(root, adminNode);
        hospitalStructure.addNode(root, medicalNode);
        hospitalStructure.addNode(adminNode, "Insan Kaynaklari");
        hospitalStructure.addNode(adminNode, "Muhasebe");
        hospitalStructure.addNode(adminNode, "Psikoloji");

        MGTNode<String> kbbNode = new MGTNode<>(kbbDept.name);
        MGTNode<String> dahiliyeNode = new MGTNode<>(dahiliyeDept.name);
        MGTNode<String> pskNode = new MGTNode<>(psikolojiDept.name);
        MGTNode<String> acilNode = new MGTNode<>("Acil Servis");

        hospitalStructure.addNode(medicalNode, acilNode);
        hospitalStructure.addNode(medicalNode, kbbNode);
        hospitalStructure.addNode(medicalNode, dahiliyeNode);
        hospitalStructure.addNode(medicalNode, pskNode);

        addDoctorsToTree(kbbNode, kbbDept);
        addDoctorsToTree(dahiliyeNode, dahiliyeDept);
        addDoctorsToTree(pskNode, psikolojiDept);
    }

    private void addDoctorsToTree(MGTNode<String> deptNode, Department dept) {
        for (int i = 0; i < dept.doctorCount; i++) {
            MGTNode<String> docNode = new MGTNode<>(dept.doctorsList[i].name);
            deptNode.addChild(docNode);
        }
    }

    public int generateID() {
        return 100000000 + random.nextInt(900000000);
    }

    public void registerPatient(String name, int age, boolean isEmergency, int severity, Doctor selectedDoctor) {
        int id = generateID();
        int priority = isEmergency ? severity : 1;

        Patient newPatient = new Patient(name, priority, age, id);

        // 1. Hash Tablosuna Ekle
        patientTable.put(id, newPatient);

        if (isEmergency) {
            emergencyQueue.add(new ERPatient(newPatient));
            System.out.println("⚠️ [ACİL] Hasta Kaydedildi: " + name + " (ID: " + id + ")");
        } else {
            if (selectedDoctor != null) {
                selectedDoctor.waitingLine.enqueue(newPatient);
                System.out.println("✅ Randevu Oluşturuldu: " + selectedDoctor.name + " -> " + name + " (ID: " + id + ")");
            }
            normalQueue.enqueue(newPatient);
        }

        patientArchive.insert(newPatient);
        undoStack.push(id);
    }

    public void showAllQueues() {
        System.out.println("\n=== GÜNCEL DURUM ===");
        System.out.println("--- 🚑 ACİL SERVİS ---");
        emergencyQueue.printQueue();

        System.out.println("\n--- 👨‍⚕️ DOKTOR KUYRUKLARI ---");
        printDeptQueue(kbbDept);
        printDeptQueue(dahiliyeDept);
        printDeptQueue(psikolojiDept);
    }

    private void printDeptQueue(Department dept) {
        System.out.println("[" + dept.name + "]");
        for(int i=0; i<dept.doctorCount; i++) {
            Doctor d = dept.doctorsList[i];
            System.out.println("  Dr. " + d.name + " (ID: " + d.doctorID + ") Sırası:");
            if(d.waitingLine.isEmpty()) System.out.println("    (Boş)");
            else d.waitingLine.printQueue();
        }
    }

    public void processUndo() {
        if (undoStack.isEmpty()) {
            System.out.println("❌ Geri alınacak işlem yok!");
            return;
        }
        Integer lastId = undoStack.pop();

        patientTable.remove(lastId);

        if (emergencyQueue.removeById(lastId)) {
            System.out.println("🔙 UNDO: Hasta Acil'den silindi. (ID: " + lastId + ")");
            return;
        }

        Patient dummy = new Patient();
        dummy.setPatientID(lastId);
        normalQueue.remove(dummy);
        removeFromAllDoctors(kbbDept, dummy);
        removeFromAllDoctors(dahiliyeDept, dummy);
        removeFromAllDoctors(psikolojiDept, dummy);

        System.out.println("🔙 UNDO: Hasta sistemden ve doktor sırasından silindi. (ID: " + lastId + ")");
    }

    private void removeFromAllDoctors(Department dept, Patient p) {
        for(int i=0; i<dept.doctorCount; i++) {
            dept.doctorsList[i].waitingLine.remove(p);
        }
    }

    public void showHierarchy() {
        System.out.println("\n--- 🏢 HASTANE HİYERARŞİSİ (General Tree) ---");
        hospitalStructure.printTree(hospitalStructure.root, "");
    }
}