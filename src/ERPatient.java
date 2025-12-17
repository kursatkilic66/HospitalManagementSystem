package src;

public class ERPatient implements Comparable<ERPatient> {
    Patient patient;

    public ERPatient(Patient p) {
        this.patient = p;
    }

    @Override
    public int compareTo(ERPatient other) {
        return Integer.compare(this.patient.getPriorityLevel(), other.patient.getPriorityLevel());
    }
    @Override
    public String toString() {
        return patient.toString() + " [Derece: " + patient.getPriorityLevel() + "]";
    }
}