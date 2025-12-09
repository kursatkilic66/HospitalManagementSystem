package src;

import java.util.Scanner;

public class HospitalApp {
//    public static void main(String[] args) {
//        //src.MyLinkedList kısmının testi için yazılan kod(src.Visit,src.Node,src.MyLinkedList)
//        /*Visit newVisit = new Visit("Ali","perianal apse","rektal tuşe");
//        Visit newVisit2 = new Visit("Veli","kafa gidik","pskilojik");
//        Visit newVisit3 = new Visit("Ali2","perianal apse2","rektal tuşe2");
//        Visit newVisit4 = new Visit("Veli2","kafa gidik2","pskilojik2");
//        MyLinkedList patientVisits = new MyLinkedList();
//        MyLinkedList patientVisits2 = new MyLinkedList();
//        patientVisits.add(newVisit);
//        patientVisits.add(newVisit2);
//        patientVisits2.add(newVisit3);
//        patientVisits2.add(newVisit4);
//        //patientVisits.printList();
//        //patientVisits.size();
//
//        MyQueue waitingLine = new MyQueue();
//        Patient p = new Patient(patientVisits,"Abuzittin",10,74,1);
//        Patient p2 = new Patient(patientVisits2,"Bbuzittin2",1,45,2);
//        Patient p3 = new Patient(patientVisits,"Cbuzittin",10,74,1);
//        Patient p4 = new Patient(patientVisits2,"Dbuzittin2",1,45,2);
//        Patient p5 = new Patient(patientVisits,"Ebuzittin",10,74,1);
//        Patient p6 = new Patient(patientVisits2,"Fbuzittin2",1,45,2);
//        //waitingLine.enqueue(p);
//        //waitingLine.enqueue(p2);
//        //System.out.println(waitingLine.peek().printPatient());
//        //System.out.println(waitingLine.printLine());
//        MyBST patientList = new MyBST();
//        patientList.insert(p);
//        patientList.insert(p5);
//        patientList.insert(p2);
//        patientList.insert(p6);
//        patientList.insert(p3);
//        patientList.insert(p4);
//
//        System.out.println(patientList.isEmpty());
//        patientList.inOrder();
//        patientList.search("Cbuzittin");*/
//    }
    static Scanner scanner = new Scanner(System.in);

    // Enter'a basılmasını bekleyen yardımcı metod
    public static void promptEnterKey() {
        System.out.println("\n>> Devam etmek için ENTER'a basınız...");
        scanner.nextLine();
    }
    public static void main(String[] args) {
        long studentNo = 221450000L;
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
                    // Normal hasta için severity önemsiz (1 gönderiyoruz)
                    hospital.registerPatient(nName, nAge, false, 1);
                    promptEnterKey(); // İŞLEM BİTİNCE BEKLE
                    break;

                case 2:
                    System.out.print("ACİL Hasta Adı: ");
                    String eName = scanner.nextLine();
                    System.out.print("Yaş: ");
                    int eAge = Integer.parseInt(scanner.nextLine());

                    int severity = 0;
                    while(severity < 1 || severity > 10) {
                        System.out.print("Aciliyet Derecesi (1-10 arası, 10 en acil): ");
                        try {
                            severity = Integer.parseInt(scanner.nextLine());
                        } catch(Exception e) {}
                    }

                    hospital.registerPatient(eName, eAge, true, severity);
                    promptEnterKey(); // İŞLEM BİTİNCE BEKLE
                    break;

                case 3:
                    // ÖNCEKİ KOD: System.out.println("Sıralar...");
                    // YENİ KOD:
                    hospital.showAllQueues();
                    promptEnterKey();
                    break;

                case 4:
                    // ÖNCEKİ KOD: System.out.println("Ağaç yapısı...");
                    // YENİ KOD:
                    hospital.showHierarchy();
                    promptEnterKey();
                    break;

                case 5:
                    hospital.processUndo();
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
