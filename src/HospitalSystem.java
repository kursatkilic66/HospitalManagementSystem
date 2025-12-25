package src;

import java.util.Random;

public class HospitalSystem {

    public MyPriorityQueue<ERPatient> emergencyQueue;
    public MyQueue<Patient> normalQueue;
    public MyStack<Integer> undoStack;
    public MyGeneralTree<String> hospitalStructure;
    public MyBST<Patient> patientArchive;

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

        this.doctorTable = new MyHashTable<>(50);
        this.patientTable = new MyHashTable<>(500);

        System.out.println("System is Opening... Creating Doctor's IDs...");
        System.out.println("-----------------------------------------------------");

        initializeDepartmentsAndDoctors();

        System.out.println("-----------------------------------------------------");

        initializeTree();
    }

    private void initializeDepartmentsAndDoctors() {
        kbbDept = new Department("Otorhinolaryngology", 5);
        dahiliyeDept = new Department("Internal Medicine", 5);
        psikolojiDept = new Department("Physiology", 5);

        Doctor d1 = new Doctor(generateID(), "Yusuf Emre Güntekin", "Otorhinolaryngology");
        kbbDept.addDoctor(d1);
        doctorTable.put(d1.doctorID, d1);
        System.out.println("️ Doctor: " + d1.name + " | ID: " + d1.doctorID);
        // --- Kulak Burun Boğaz Doktorları ---
        Doctor d2 = new Doctor(generateID(), "Mehmet Kaplan", "Otorhinolaryngology");
        kbbDept.addDoctor(d2);
        doctorTable.put(d2.doctorID, d2);
        System.out.println(" Doctor: " + d2.name + " | ID: " + d2.doctorID);

        // --- Dahiliye Doktorları ---
        Doctor d3 = new Doctor(generateID(), "Türker Emre Kuru", "Internal Medicine");
        dahiliyeDept.addDoctor(d3);
        doctorTable.put(d3.doctorID, d3);
        System.out.println(" Doctor: " + d3.name + " | ID: " + d3.doctorID);

        Doctor d4 = new Doctor(generateID(), "Arda Yaşar", "Internal Medicine");
        dahiliyeDept.addDoctor(d4);
        doctorTable.put(d4.doctorID, d4);
        System.out.println("️ Doctor: " + d4.name + " | ID: " + d4.doctorID);

        // --- Psikoloji Doktorları ---
        Doctor d5 = new Doctor(generateID(), "Mert Kaçmaz", "Physiology");
        psikolojiDept.addDoctor(d5);
        doctorTable.put(d5.doctorID, d5);
        System.out.println("️ Doctor: " + d5.name + " | ID: " + d5.doctorID);
    }

    private void initializeTree() {
        MGTNode<String> root = new MGTNode<>("Medical Directorate");
        hospitalStructure = new MyGeneralTree<>(root);

        MGTNode<String> adminNode = new MGTNode<>("Administration");
        MGTNode<String> medicalNode = new MGTNode<>("Medical Departments");

        hospitalStructure.addNode(root, adminNode);
        hospitalStructure.addNode(root, medicalNode);
        hospitalStructure.addNode(adminNode, "Human Resources");
        hospitalStructure.addNode(adminNode, "Accounting");
        //hospitalStructure.addNode(adminNode, "Physiology");

        MGTNode<String> kbbNode = new MGTNode<>(kbbDept.name);
        MGTNode<String> dahiliyeNode = new MGTNode<>(dahiliyeDept.name);
        MGTNode<String> pskNode = new MGTNode<>(psikolojiDept.name);
        MGTNode<String> acilNode = new MGTNode<>("Emergency Service");

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

        patientTable.put(id, newPatient);

        if (isEmergency) {
            emergencyQueue.add(new ERPatient(newPatient));
            System.out.println("[EMERGENCY] Patient Has Registered: " + name + " (ID: " + id + ")");
        } else {
            if (selectedDoctor != null) {
                selectedDoctor.waitingLine.enqueue(newPatient);
                System.out.println("Appointment created successfully: " + selectedDoctor.name + " -> " + name + " (ID: " + id + ")");
            }
            normalQueue.enqueue(newPatient);
        }

        patientArchive.insert(newPatient);
        undoStack.push(id);
    }

    public void showAllQueues() {
        System.out.println("\n=== CURRENT SITUATIONS ===");
        System.out.println("--- EMERGENCY SERVICE ---");
        emergencyQueue.printQueue();

        System.out.println("\n--- DOCTOR QUEUES ---");
        printDeptQueue(kbbDept);
        printDeptQueue(dahiliyeDept);
        printDeptQueue(psikolojiDept);
    }

    private void printDeptQueue(Department dept) {
        System.out.println("[" + dept.name + "]");
        for(int i=0; i<dept.doctorCount; i++) {
            Doctor d = dept.doctorsList[i];
            System.out.println("  Doc. " + d.name + " (ID: " + d.doctorID + ") His Queue:");
            if(d.waitingLine.isEmpty()) System.out.println("    (Empty)");
            else d.waitingLine.printQueue();
        }
    }

    public void processUndo() {
        if (undoStack.isEmpty()) {
            System.out.println(" There Is Nothing To Undo!");
            return;
        }
        Integer lastId = undoStack.pop();

        patientTable.remove(lastId);

        if (emergencyQueue.removeById(lastId)) {
            System.out.println(" UNDO: Patient Deleted From Emergency Queue. (ID: " + lastId + ")");
            return;
        }

        Patient dummy = new Patient();
        dummy.setPatientID(lastId);
        normalQueue.remove(dummy);
        removeFromAllDoctors(kbbDept, dummy);
        removeFromAllDoctors(dahiliyeDept, dummy);
        removeFromAllDoctors(psikolojiDept, dummy);

        System.out.println(" UNDO: Patient Deleted From Queues. (ID: " + lastId + ")");
    }

    private void removeFromAllDoctors(Department dept, Patient p) {
        for(int i=0; i<dept.doctorCount; i++) {
            dept.doctorsList[i].waitingLine.remove(p);
        }
    }

    public void showHierarchy() {
        System.out.println("\n--- HOSPITAL HIERARCHY (General Tree) ---");
        hospitalStructure.printTree(hospitalStructure.root, "");
    }
}