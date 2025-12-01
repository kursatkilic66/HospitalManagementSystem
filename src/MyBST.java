package src;

public class MyBST {
    BSTNode root;
    public void insert(Patient p) {
        BSTNode newNode = new BSTNode(p);

        // 1. Ağaç boşsa direkt kök yap
        if (isEmpty()) {
            root = newNode;
            return;
        }

        BSTNode temp = root;

        while (true) {
            // İsimleri karşılaştır
            // compareTo < 0 ise: temp(root) < newNode. Yani yeni gelen daha BÜYÜK (veya alfabetik olarak sonra).
            // compareTo > 0 ise: temp(root) > newNode. Yani yeni gelen daha KÜÇÜK.

            int comparison = temp.getData().getName().compareTo(newNode.getData().getName());

            if (comparison > 0) {
                // SOLA GİTMELİ (Yeni gelen daha küçük/önce)
                if (temp.left == null) {
                    temp.left = newNode; // Yer boşsa, buraya bağla
                    return; // ve işlem bitti, çık.
                }
                temp = temp.left; // Doluysa sola ilerle
            }
            else if (comparison < 0) {
                // SAĞA GİTMELİ (Yeni gelen daha büyük/sonra)
                if (temp.right == null) {
                    temp.right = newNode; // Yer boşsa, buraya bağla
                    return; // ve işlem bitti, çık.
                }
                temp = temp.right; // Doluysa sağa ilerle
            }
            else {
                // İsimler EŞİTSE ne yapılacağına karar ver (Genelde eklenmez veya update edilir)
                return;
            }
        }
    }
    public void search(String name) {
        if(isEmpty()) {
            System.out.println("Patient List is Empty!");
            return;
        }
        BSTNode temp = root;
        while(true){
            int comparison = temp.getData().getName().compareTo(name);
            if(comparison == 0) {
                System.out.println("User Has Found!");
                temp.getData().printPatientVoid();
                return;
            }
            else if(comparison > 0) {
                if(temp.left == null) {
                    System.out.println("User Is Not Exists!");
                    return;
                }
                temp = temp.left;
            }
            else {
                if(temp.right == null) {
                    System.out.println("User Is Not Exists");
                    return;
                }
                temp = temp.right;
            }
        }
    }
    public void inOrder() {
        if(isEmpty()) {
            System.out.println("Patient List is Empty!");
            return;
        }
        inorderRecursive(root);
    }
    private void inorderRecursive(BSTNode node) {
        if(node == null) {
            return;
        }
        inorderRecursive(node.left);
        System.out.println(node.getData().getName());
        inorderRecursive(node.right);
    }
    public boolean isEmpty() {
        return root == null;
    }
}
