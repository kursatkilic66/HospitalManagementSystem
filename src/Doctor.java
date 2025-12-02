package src;

public class Doctor {
    int doctorID;
    String name,department;
    MyQueue waitingLine;

    public void addPatient(Patient p) {

    }
    public void callNextPatient() {

    }

    public String toString(String choice) {
        return switch (choice) {
            case "name" -> String.format("-Name: %s", name);
            case "all" -> String.format(
                    "-ID: %d\n-Department: %s\n-Name: %s\n----------------",
                    this.doctorID,
                    this.department,
                    this.name
                    //this.age
                    //this.medicalHistory.printList() // Burası için aşağıya not düştüm*
            );
            default -> null;
        };
    }
}
