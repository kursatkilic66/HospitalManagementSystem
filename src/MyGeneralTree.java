//package src;
//
//public class MyGeneralTree<T extends  Comparable<T>> {
//    MGTNode<T> root;
//
//    public MyGeneralTree(MGTNode<T> root) {
//        this.root = root;
//    }
//
//    public void addNode(MGTNode<T> parent, T childData) {
//        MGTNode<T> newChild = new MGTNode<>(childData);
//        parent.addChild(newChild);
//    }
//    public void remove(T data) {
//        if(isEmpty()) {
//            System.out.println("There Isn't Any Information About This Hospital!");
//            return;
//        }
//        MGTNode<T> temp = root;
//        MGTNode<T> removeNode = new MGTNode<>(data);
//        MGTNode<T> foundedNode = search(data,temp);
//
//    }
//
//    public MGTNode<T> search(T searchingObject,MGTNode<T> temp) {
//        while(true) {
//            int comparison = searchingObject.compareTo(temp.data);
//
//            if(comparison == 0) {
//                return temp;
//            }
//            else {
//                temp = temp.children.
//            }
//        }
//
//
//    }
//    public void printTree(MGTNode<T> node, String prefix) {
//        if (node == null) return;
//
//        System.out.println(prefix + "└── " + node.data.toString());
//
//        Node temp = node.children.getHead(); // Listenin başına git
//        while (temp != null) {
//            printTree((MGTNode) temp.data, prefix + "    "); // Girintiyi artır
//            temp = temp.next;
//        }
//    }
//    public boolean isEmpty() {
//        return root == null;
//    }
//}

package src;


public class MyGeneralTree<T extends Comparable<T>> {
    MGTNode<T> root;

    public MyGeneralTree(MGTNode<T> root) {
        this.root = root;
    }

    public void remove(T data) {
        if (isEmpty()) {
            System.out.println("Tree is empty!");
            return;
        }

        if (root.data.compareTo(data) == 0) {
            root = null; // Ağacı tamamen sil
            System.out.println(data + " (Root) ve altındaki her şey silindi.");
            return;
        }

        boolean isRemoved = removeRecursive(root, data);

        if (isRemoved) {
            System.out.println(data + " başarıyla silindi.");
        } else {
            System.out.println(data + " bulunamadı.");
        }
    }

    private boolean removeRecursive(MGTNode<T> parent, T dataToRemove) {
        if (parent.children == null || parent.children.isEmpty()) {
            return false;
        }


        Node<MGTNode<T>> current = parent.children.getHead();

        while (current != null) {
            MGTNode<T> childNode = current.data; // Sıradaki çocuk

            if (childNode.data.compareTo(dataToRemove) == 0) {

                parent.children.remove(childNode);
                return true;
            }

            boolean foundInSubTree = removeRecursive(childNode, dataToRemove);
            if (foundInSubTree) {
                return true;
            }

            current = current.next;
        }

        return false;
    }


    public MGTNode<T> search(T dataToFind) {
        return searchRecursive(root, dataToFind);
    }

    private MGTNode<T> searchRecursive(MGTNode<T> currentNode, T dataToFind) {
        if (currentNode == null) return null;

        // 1. Bulduk mu?
        if (currentNode.data.compareTo(dataToFind) == 0) {
            return currentNode;
        }

        // 2. Bulamadıysak çocuklarını gez
        Node<MGTNode<T>> temp = currentNode.children.getHead();

        while (temp != null) {
            // Recursive çağrı: Çocuğun içine gir ve ara
            MGTNode<T> result = searchRecursive(temp.data, dataToFind);

            // Eğer alt taraftan dolu bir sonuç döndüyse, bulduk demektir. Yukarı taşı.
            if (result != null) {
                return result;
            }
            temp = temp.next;
        }

        // Hiçbir yerde yoksa null dön
        return null;
    }

    public void addNode(MGTNode<T> parent, T childData) {
        MGTNode<T> newChild = new MGTNode<>(childData);
        parent.addChild(newChild);
    }


    public void addNode(MGTNode<T> parent, MGTNode<T> childNode) {
        parent.addChild(childNode);
    }

    public void printTree(MGTNode<T> node, String prefix) {
        if (node == null) return;

        System.out.println(prefix + "└── " + node.data.toString());


        Node<MGTNode<T>> temp = node.children.getHead();
        while (temp != null) {
            printTree(temp.data, prefix + "    ");
            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
