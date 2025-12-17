package src;

public class Doctor {
    int doctorID;
    String name;
    String department;
    MyQueue<Patient> waitingLine;

    public Doctor(int doctorID, String name, String department) {
        this.doctorID = doctorID;
        this.name = name;
        this.department = department;
        this.waitingLine = new MyQueue<Patient>();
    }
    public void addPatient(Patient p) {
        waitingLine.enqueue(p);
    }

    @Override
    public String toString() {
        return name + " (" + department + ")";
    }
}
