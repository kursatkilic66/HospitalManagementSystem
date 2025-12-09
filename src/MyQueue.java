package src;

public class MyQueue<T> {
    private Node<T> head,rear;
    public void enqueue(T p) {
        Node<T> newPatient = new Node<T>(p);
        if(isEmpty()) {
            head = rear = newPatient;
            return;
        }
        rear.next = newPatient;
        rear = newPatient;
    }
    public T dequeue() {
        if(isEmpty()) {
            System.out.println("Waiting Line is Empty!");
            return null;
        }
        T removedPatient = head.data;
        head = head.next;
        if(isEmpty()) {
            rear = null;
        }
        return removedPatient;
    }
    public T peek() {
        if (isEmpty()) {
            System.out.println("Waiting Line is Empty!");
            return null;
        }
        /*System.out.println("---WAITING LINE---");
        System.out.printf("-ID: %d\n-Priority: %d\n-Name: %s\n-Age %d\n",head.data.getPatientID(),head.data.getPriorityLevel(),head.data.getName(),head.data.getAge());
*/
        return head.data;
    }
    public void remove(T dataToRemove) {
        if (isEmpty()) {
            return;
        }

        // 1. DURUM: Silinecek eleman kuyruğun EN BAŞINDAYSA (Head)
        // (Patient sınıfına eklediğin equals metodu sayesinde ID kontrolü yapar)
        if (head.data.equals(dataToRemove)) {
            head = head.next;
            if (head == null) { // Eğer listede tek eleman vardıysa ve sildiysek
                rear = null;    // Rear da null olmalı
            }
            return;
        }

        // 2. DURUM: Eleman ARADA veya SONDA ise
        Node<T> current = head;
        while (current.next != null) {
            // Bir sonraki düğüm aradığımız veri mi?
            if (current.next.data.equals(dataToRemove)) {

                // Eğer sildiğimiz düğüm kuyruğun sonuncusuysa (Rear),
                // Rear'ı şu anki düğüme (bir gerisine) çekmeliyiz.
                if (current.next == rear) {
                    rear = current;
                }

                // Bağlantıyı atlatarak düğümü sil (current -> next -> next)
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }

        System.out.println("Silinecek kayıt normal kuyrukta bulunamadı.");
    }
    public boolean isEmpty() {return head == null;}

    // Normal kuyruktaki herkesi yazdırır
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("    (Kuyruk Boş)");
            return;
        }
        Node<T> temp = head;
        int count = 1;
        while (temp != null) {
            System.out.println("    " + count + ". " + temp.data.toString());
            temp = temp.next;
            count++;
        }
    }
    /*public String printLine() {
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
    }*/
}
