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
    public void printList() {
        /*
            sout("---PATIENT'S VISITS--")
            src.Node temp = head;
            while(temp's next is not null)
            1.sout("-Date: date\n-src.Doctor's name: doctorName\n-Diagnosis:diagnosis\n-Treatment:treatment")
            2.sout("--------------")
            3.temp.next = temp

         */
        if(isEmpty()) {
            System.out.println("There is no visit!");
            return;
        }
        System.out.println("---PATIENT'S VISITS---");
        Node<Visit> temp = head;
        while (temp != null) {
            System.out.printf("-Date: %td/%<tm/%<tY\n-src.Doctor's name: %s\n-Diagnosis: %s\n-Treatment: %s\n",temp.data.getDate(),temp.data.getDoctorName(),temp.data.getDiagnosis(),temp.data.getTreatment());
            System.out.println("-------------------");
            temp = temp.next;
        }
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
