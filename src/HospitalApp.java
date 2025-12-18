package src;

import java.util.Scanner;

public class HospitalApp {
    static Scanner scanner = new Scanner(System.in);

    public static void promptEnterKey() {
        System.out.println("\n>> Devam etmek için ENTER'a basınız...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        long studentNo = 230316055L; // Seed olarak kullanılıyor
        HospitalSystem hospital = new HospitalSystem(studentNo);
        boolean running = true;

        while (running) {
            System.out.println("\n===============================");
            System.out.println("   HASTANE YÖNETİM PANELİ");
            System.out.println("===============================");
            System.out.println("1 - Normal Hasta Kayıt");
            System.out.println("2 - ⚠️ ACİL Hasta Kayıt (Dereceli)");
            System.out.println("3 - Sıraları Görüntüle");
            System.out.println("4 - Hastane Hiyerarşisi");
            System.out.println("5 - Son İşlemi Geri Al (Undo)");
            System.out.println("6 - ID ile Doktor Ara (Hash Table)");
            System.out.println("7 - ID ile Hasta Ara (Hash Table)");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminiz: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş!");
                promptEnterKey();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Hasta Adı: ");
                    String nName = scanner.nextLine();
                    System.out.print("Yaş: ");
                    int nAge = Integer.parseInt(scanner.nextLine());

                    System.out.println("\n--- BÖLÜM SEÇİNİZ ---");
                    System.out.println("1 - Kulak Burun Boğaz");
                    System.out.println("2 - Dahiliye");
                    System.out.println("3 - Psikoloji");
                    System.out.print("Seçim: ");
                    int deptChoice = Integer.parseInt(scanner.nextLine());

                    Department selectedDept = null;
                    if (deptChoice == 1) selectedDept = hospital.kbbDept;
                    else if (deptChoice == 2) selectedDept = hospital.dahiliyeDept;
                    else if (deptChoice == 3) selectedDept = hospital.psikolojiDept;
                    else {
                        System.out.println("Geçersiz Bölüm!");
                        break;
                    }

                    System.out.println("\n--- DOKTOR SEÇİNİZ ---");
                    selectedDept.listDoctors();
                    System.out.print("Seçim (Sıra No): ");
                    try {
                        int docIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (docIndex >= 0 && docIndex < selectedDept.doctorCount) {
                            Doctor selectedDoctor = selectedDept.doctorsList[docIndex];
                            hospital.registerPatient(nName, nAge, false, 1, selectedDoctor);
                        } else {
                            System.out.println("Geçersiz Doktor Seçimi!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Hata: Sayı giriniz.");
                    }
                    promptEnterKey();
                    break;

                case 2:
                    System.out.print("ACİL Hasta Adı: ");
                    String eName = scanner.nextLine();
                    System.out.print("Yaş: ");
                    int eAge = Integer.parseInt(scanner.nextLine());

                    int severity = 0;
                    while (severity < 1 || severity > 10) {
                        System.out.print("Aciliyet Derecesi (1-10 arası, 10 en acil): ");
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
                    System.out.println("\n--- 🔎 DOKTOR ARAMA ---");
                    // İpucu: Listeleme sırasında doktor ID'lerini görebilirsiniz.
                    System.out.print("Aranacak Doktor ID: ");
                    System.out.println();
                    try {
                        int searchDocId = Integer.parseInt(scanner.nextLine());
                        Doctor foundDoc = hospital.doctorTable.get(searchDocId);

                        if (foundDoc != null) {
                            System.out.println("✅ DOKTOR BULUNDU:");
                            System.out.println("   ID: " + foundDoc.doctorID);
                            System.out.println("   İsim: " + foundDoc.name);
                            System.out.println("   Bölüm: " + foundDoc.department);
                        } else {
                            System.out.println("❌ Bu ID'ye sahip doktor bulunamadı.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Hatalı ID formatı!");
                    }
                    promptEnterKey();
                    break;

                case 7: // ID ile Hasta Ara
                    System.out.println("\n--- 🔎 HASTA ARAMA ---");
                    System.out.print("Aranacak Hasta ID: ");
                    try {
                        int searchPatId = Integer.parseInt(scanner.nextLine());
                        Patient foundPat = hospital.patientTable.get(searchPatId);

                        if (foundPat != null) {
                            System.out.println("✅ HASTA BULUNDU:");
                            System.out.println("   ID: " + foundPat.getPatientID());
                            System.out.println("   İsim: " + foundPat.getName());
                            System.out.println("   Yaş: " + foundPat.getAge());
                            System.out.println("   Aciliyet: " + (foundPat.getPriorityLevel() > 1 ? "ACİL (" + foundPat.getPriorityLevel() + ")" : "Normal"));
                        } else {
                            System.out.println("❌ Bu ID'ye sahip hasta sistemde yok (veya silinmiş).");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Hatalı ID formatı!");
                    }
                    promptEnterKey();
                    break;

                case 0:
                    running = false;
                    System.out.println("Çıkış yapılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim!");
                    promptEnterKey();
            }
        }
    }
}
