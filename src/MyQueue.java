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
        return head.data;
    }
    public void remove(T dataToRemove) {
        if (isEmpty()) {
            return;
        }


        if (head.data.equals(dataToRemove)) {
            head = head.next;
            if (head == null) {
                rear = null;
            }
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(dataToRemove)) {
                if (current.next == rear) {
                    rear = current;
                }
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }

        System.out.println("Silinecek kayıt normal kuyrukta bulunamadı.");
    }
    public boolean isEmpty() {return head == null;}

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
}
