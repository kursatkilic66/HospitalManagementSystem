package src;

public class Patient {
    private int patientID,age,priorityLevel;
    private String name;
    private MyLinkedList<Visit> medicalHistory;
    public void addVisit() {}
    public void getHistory() {

    }

    public Patient() {
    }
    public Patient(MyLinkedList<Visit> medicalHistory, String name, int priorityLevel, int age, int patientID) {
        this.medicalHistory = medicalHistory;
        this.name = name;
        this.priorityLevel = priorityLevel;
        this.age = age;
        this.patientID = patientID;
    }

    public String printPatient() {
        // String.format aynen printf gibi çalışır ama ekrana yazmaz, metni döndürür.
        return String.format(
                "---WAITING LINE---\n-ID: %d\n-Priority: %d\n-Name: %s\n-Age: %d\n----------------",
                this.patientID,
                this.priorityLevel,
                this.name,
                this.age
                //this.medicalHistory.printList() // Burası için aşağıya not düştüm*
        );
    }

    public void printPatientVoid() {
        System.out.println("---PATIENT'S INFORMATION---");
        System.out.printf("-ID: %d\n-Priority: %d\n-Name: %s\n-Age: %d\n",
                this.patientID,
                this.priorityLevel,
                this.name,this.age);
        System.out.println("------------------------");
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

    public MyLinkedList<Visit> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MyLinkedList<Visit> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /*public String toString(String choice) {
        return switch (choice) {
            case "name" -> String.format("-Name: %s", getName());
            case "all" -> String.format(
                    "-ID: %d\n-Priority: %d\n-Name: %s\n-Age: %d\n----------------",
                    this.patientID,
                    this.priorityLevel,
                    this.name,
                    this.age
                    //this.medicalHistory.printList() // Burası için aşağıya not düştüm*
            );
            default -> null;
        };
    }*/
}
