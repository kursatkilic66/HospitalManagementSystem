package src;

public class MyQueue {
    private Node<Patient> head,rear;
    public void enqueue(Patient p) {
        Node<Patient> newPatient = new Node<>(p);
        if(isEmpty()) {
            head = rear = newPatient;
            return;
        }
        rear.next = newPatient;
        rear = newPatient;
    }
    public Patient dequeue() {
        if(isEmpty()) {
            System.out.println("Waiting Line is Empty!");
            return null;
        }
        Patient removedPatient = head.data;
        head = head.next;
        if(isEmpty()) {
            rear = null;
        }
        return removedPatient;
    }
    public Patient peek() {
        if (isEmpty()) {
            System.out.println("Waiting Line is Empty!");
            return null;
        }
        /*System.out.println("---WAITING LINE---");
        System.out.printf("-ID: %d\n-Priority: %d\n-Name: %s\n-Age %d\n",head.data.getPatientID(),head.data.getPriorityLevel(),head.data.getName(),head.data.getAge());
*/
        return head.data;
    }
    public boolean isEmpty() {return head == null;}
    public String printLine() {
        // 1. Liste boşsa direkt mesajı döndür
        if (isEmpty()) {
            return "Waiting Line is Empty!";
        }

        StringBuilder sb = new StringBuilder();

        // Başlık
        sb.append("---WAITING LINE---\n");

        Node<Patient> temp = head;
        while (temp != null) {
            // 2. printf yerine String.format kullanarak formatlı metni hazırla
            String patientInfo = String.format(
                    "-ID: %d%n-Priority: %d%n-Name: %s%n-Age: %d%n",
                    temp.data.getPatientID(),
                    temp.data.getPriorityLevel(),
                    temp.data.getName(),
                    temp.data.getAge()
                    //temp.data.getMedicalHistory()
            );

            // 3. Hazırlanan metni StringBuilder'a ekle
            sb.append(patientInfo);
            sb.append("----------------\n"); // Ayırıcı çizgi ve alt satır

            temp = temp.next;
        }

        // 4. Tüm StringBuilder'ı String'e çevirip döndür
        return sb.toString();
    }
}
