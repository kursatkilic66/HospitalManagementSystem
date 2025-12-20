package src;

import java.util.Scanner;

public class HospitalApp {
    static Scanner scanner = new Scanner(System.in);

    public static void promptEnterKey() {
        System.out.println("\n>> Press ENTER to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        long studentNo = 230316055L;
        HospitalSystem hospital = new HospitalSystem(studentNo);
        boolean running = true;

        while (running) {
            System.out.println("\n===============================");
            System.out.println("   HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("===============================");
            System.out.println("1 - Normal Patient Register");
            System.out.println("2 - EMERGENCY Patient Register (With Priority Level)");
            System.out.println("3 - View The Queues");
            System.out.println("4 - Hospital Hierarchy");
            System.out.println("5 - Undo The Last Process(UNDO)");
            System.out.println("6 - Search Doctor By ID ");
            System.out.println("7 - Search Patient By ID ");
            System.out.println("0 - Exit");
            System.out.print("Your Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Process!");
                promptEnterKey();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Patient Name: ");
                    String nName = scanner.nextLine();
                    System.out.print("Age: ");
                    int nAge = Integer.parseInt(scanner.nextLine());

                    System.out.println("\n--- CHOOSE A DEPARTMENT ---");
                    System.out.println("1 - Otorhinolaryngology");
                    System.out.println("2 - Internal Medicine");
                    System.out.println("3 - Psychology");
                    System.out.print("Your Choice: ");
                    int deptChoice = Integer.parseInt(scanner.nextLine());

                    Department selectedDept = null;
                    if (deptChoice == 1) selectedDept = hospital.kbbDept;
                    else if (deptChoice == 2) selectedDept = hospital.dahiliyeDept;
                    else if (deptChoice == 3) selectedDept = hospital.psikolojiDept;
                    else {
                        System.out.println("Invalid Process!");
                        break;
                    }

                    System.out.println("\n--- CHOOSE A DOCTOR ---");
                    selectedDept.listDoctors();
                    System.out.print("Choice (Queue Number): ");
                    try {
                        int docIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (docIndex >= 0 && docIndex < selectedDept.doctorCount) {
                            Doctor selectedDoctor = selectedDept.doctorsList[docIndex];
                            hospital.registerPatient(nName, nAge, false, 1, selectedDoctor);
                        } else {
                            System.out.println("Invalid Doctor Choice!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Type a Number.");
                    }
                    promptEnterKey();
                    break;

                case 2:
                    System.out.print("EMERGENCY Patient Name: ");
                    String eName = scanner.nextLine();
                    System.out.print("Age: ");
                    int eAge = Integer.parseInt(scanner.nextLine());

                    int severity = 0;
                    while (severity < 1 || severity > 10) {
                        System.out.print("Emergeny Level (1 to 10, 10 Is The Most Important One): ");
                        try {
                            severity = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                        }
                    }

                    hospital.registerPatient(eName, eAge, true, severity, null);
                    promptEnterKey();
                    break;

                case 3:
                    hospital.showAllQueues();
                    promptEnterKey();
                    break;

                case 4:
                    hospital.showHierarchy();
                    promptEnterKey();
                    break;

                case 5:
                    hospital.processUndo();
                    promptEnterKey();
                    break;

                case 6: // ID ile Doktor Ara
                    System.out.println("\n--- SEARCH DOCTOR ---");
                    System.out.print("ID to Search Doctor: ");
                    System.out.println();
                    try {
                        int searchDocId = Integer.parseInt(scanner.nextLine());
                        Doctor foundDoc = hospital.doctorTable.get(searchDocId);

                        if (foundDoc != null) {
                            System.out.println("DOCTOR HAS FOUND:");
                            System.out.println("   ID: " + foundDoc.doctorID);
                            System.out.println("   Name: " + foundDoc.name);
                            System.out.println("   Department: " + foundDoc.department);
                        } else {
                            System.out.println("We Couldn't Find A Doctor With This ID.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID Format!");
                    }
                    promptEnterKey();
                    break;

                case 7: // ID ile Hasta Ara
                    System.out.println("\n--- SEARCH PATIENT ---");
                    System.out.print("ID to Search Patient: ");
                    try {
                        int searchPatId = Integer.parseInt(scanner.nextLine());
                        Patient foundPat = hospital.patientTable.get(searchPatId);

                        if (foundPat != null) {
                            System.out.println("PATIENT HAS FOUND!:");
                            System.out.println("   ID: " + foundPat.getPatientID());
                            System.out.println("   Name: " + foundPat.getName());
                            System.out.println("   Age: " + foundPat.getAge());
                            System.out.println("   Priority: " + (foundPat.getPriorityLevel() > 1 ? "EMERGENCY (" + foundPat.getPriorityLevel() + ")" : "Normal"));
                        } else {
                            System.out.println("There is no Patient With This ID (Or Deleted).");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID Format!");
                    }
                    promptEnterKey();
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
                    promptEnterKey();
            }
        }
    }
}
