package src;

public class Patient implements Comparable<Patient> {
    private int patientID,age,priorityLevel;
    private String name;

    public Patient() {
    }
    public Patient(String name, int priorityLevel, int age, int patientID) {
        this.name = name;
        this.priorityLevel = priorityLevel;
        this.age = age;
        this.patientID = patientID;
    }
    @Override
    public int compareTo(Patient other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient other = (Patient) obj;
        return this.patientID == other.patientID;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s (Age: %d, Priority: %d)",
                this.patientID, this.name, this.age, this.priorityLevel);
    }
}
