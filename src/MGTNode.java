package src;

public class MGTNode<T> {
    T data;
    MyLinkedList<MGTNode<T>> children;

    public MGTNode(T data) {
        this.data = data;
        this.children = new MyLinkedList<>();
    }

    public void addChild(MGTNode<T> child) {
        this.children.add(child);
    }
    @Override
    public String toString() {
        return data.toString();
    }
}
