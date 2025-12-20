package src;

public class MyBST<T extends  Comparable<T>> {
    BSTNode<T> root;
    public void insert(T p) {
        BSTNode<T> newNode = new BSTNode<>(p);
        if (isEmpty()) {
            root = newNode;
            return;
        }
        BSTNode<T> temp = root;
        while (true) {
            int comparison = p.compareTo(temp.data);

            if (comparison < 0) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return;
                }
                temp = temp.left;
            }
            else if (comparison > 0) {

                if (temp.right == null) {
                    temp.right = newNode;
                    return;
                }
                temp = temp.right;
            }
            else {
                System.out.println("There Is Already A Register.");
                return;
            }
        }
    }
    public T search(T dataToFind) {
        if(isEmpty()) {
            System.out.println("Patient List is Empty!");
            return null;
        }
        BSTNode<T> temp = root;
        while(temp != null){
            int comparison = dataToFind.compareTo(temp.data);
            if(comparison == 0) {
                System.out.println("User Has Found!");
                System.out.println(temp.data.toString());
                return temp.data;
            }
            else if(comparison < 0) {
                temp = temp.left;
            }
            else {
                temp = temp.right;
            }
        }
        return null;
    }
    public void inOrder() {
        if(isEmpty()) {
            System.out.println("Patient List is Empty!");
            return;
        }
        inorderRecursive(root);
    }
    private void inorderRecursive(BSTNode<T> node) {
        if(node == null) {
            return;
        }
        inorderRecursive(node.left);
        System.out.println(node.data.toString());
        inorderRecursive(node.right);
    }
    public boolean isEmpty() {
        return root == null;
    }
}
