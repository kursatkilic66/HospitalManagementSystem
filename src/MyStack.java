package src;
//
//public class MyStack {
//    Node top;
//    public void push() {}
//    public void pop() {}
//    public void peek() {}
//}

public class MyStack<T> {
    Node<T> top;
    public void push(T x) {
        Node<T> newNode = new Node<T>(x);
        if  (isEmpty()) {
            top = newNode;
            return;
        }
        newNode.next = top;
        top = newNode;
    }
    public void pop() {
        if  (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        Object topData = top.data;
        top = top.next;
        System.out.println(topData);
    }
    public void peek() {
        if  (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println(top.data);
    }
    void printStack() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        Node<T> temp = top;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    public int getSize(){
        if(isEmpty()) {
            return 0;
        }
        Node<T> temp = top;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }
    boolean isEmpty() {
        return top == null;
    }
}
