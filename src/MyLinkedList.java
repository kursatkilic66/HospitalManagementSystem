package src;

public class MyLinkedList<T> {
    private Node<T> head;
    public void add(T data) {
        /*newVNode = data
        if(head is null)
            1.head = newNode
            return
        head.next = newNode
        */
        Node<T> newPatient = new Node<T>((T) data);
        if (isEmpty()) {
            head = newPatient;
            return;
        }
        Node<T> temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = newPatient;
    }
    /*public String printList() {
        // 1. Liste boşsa direkt uyarı mesajını döndür
        if (isEmpty()) {
            return "There is no visit!";
        }

        StringBuilder sb = new StringBuilder();

        // Başlığı ekle (sonuna \n koymayı unutmuyoruz)
        sb.append("---PATIENT'S VISITS---\n");

        Node<Visit> temp = head;

        while (temp != null) {
            // 2. printf yerine String.format kullanarak aynı düzeni koruyoruz
            String formattedVisit = String.format(
                    "-Date: %td/%<tm/%<tY\n-Doctor's name: %s\n-Diagnosis: %s\n-Treatment: %s\n",
                    temp.data.getDate(),
                    temp.data.getDoctorName(),
                    temp.data.getDiagnosis(),
                    temp.data.getTreatment()
            );

            sb.append(formattedVisit);
            sb.append("-------------------\n"); // Ayırıcı çizgi

            temp = temp.next;
        }

        return sb.toString();
    }*/
    public int size() {
        Node<T> temp = head;
        if(isEmpty()) {
            System.out.println("src.Patient's src.Visit List is Empty!");
            return 0;
        }
        int size = 0;
        while(temp != null) {
            size++;
            temp = temp.next;
        }
        System.out.printf("List's size is %d",size);
        return size;
    }
    //Her kayda bir ID sağlandıktan sonra yazılacak
//    public void remove(T data) {
//        if(head == null) {
//            System.out.println("There isn't any patient visit record to remove!");
//            return;
//        }
//        Node<T> temp = head;
//        while(temp.data != null) {
//            if(temp.data == data) {
//
//            }
//        }
//
//    }
    public void remove(T dataToRemove) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.data.equals(dataToRemove)) {
            head = head.next;
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(dataToRemove)) {
                current.next = current.next.next;
                return; // İşlem tamam, çık
            }
            current = current.next;
        }
        System.out.println("Veri bulunamadı: " + dataToRemove);
    }
    public boolean isEmpty() {
        return head == null;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }
}
