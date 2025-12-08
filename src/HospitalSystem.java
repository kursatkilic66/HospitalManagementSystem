package src;

public class HospitalSystem {
    MyHashTable patientTable;
    MyBST nameSearchTree;
    MyPriorityQueue<ERPatient> emergencyRoom = new MyPriorityQueue<>(10);
    MyStack undoStack;
    MyGeneralTree hospitalStructure;
    public void registerPatient(Patient p) {

    }

    // Kuyruğu ERPatient tutacak şekilde tanımla

    public void admitToER(Patient p) {
        // Hastayı paketleyip kuyruğa at
        emergencyRoom.add(new ERPatient(p));
    }
    public void processUndo() {

    }
}
