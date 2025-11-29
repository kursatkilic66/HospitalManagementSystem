package src;

public class MyLinkedList {
    private Node<Visit> head;
    public void add(Object data) {
        /*newVNode = data
        if(head is null)
            1.head = newNode
            return
        head.next = newNode
        */
        Node<Visit> newPatient = new Node<Visit>((Visit) data);
        if (isEmpty()) {
            head = newPatient;
            return;
        }
        head.next = newPatient;
    }
    public String printList() {
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
    }
    public int size() {
        Node<Visit> temp = head;
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
    public void remove() {
        if(head == null) {
            System.out.println("There isn't any patient visit record to remove!");
            return;
        }
        Node<Visit> temp = head;

    }
    public boolean isEmpty() {
        return head == null;
    }
}
