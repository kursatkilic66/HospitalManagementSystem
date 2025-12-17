package src;

public class Department {
    String name;
    Doctor[] doctorsList;
    int doctorCount;

    public Department(String name, int capacity) {
        this.name = name;
        this.doctorsList = new Doctor[capacity];
        this.doctorCount = 0;
    }

    public void addDoctor(Doctor doc) {
        if (doctorCount < doctorsList.length) {
            doctorsList[doctorCount] = doc;
            doctorCount++;
        }
    }

    public void listDoctors() {
        for (int i = 0; i < doctorCount; i++) {
            System.out.println((i + 1) + " - " + doctorsList[i].name);
        }
    }
}
