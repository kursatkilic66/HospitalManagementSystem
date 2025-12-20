package src;

public class MyLinkedList<T> {
    private Node<T> head;
    public void add(T data) {
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
                return;
            }
            current = current.next;
        }
        System.out.println("Data Is Not Found!: " + dataToRemove);
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
