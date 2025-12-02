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
                // SOLA GİT (Yeni gelen daha küçük/önce)
                if (temp.left == null) {
                    temp.left = newNode; // Yer boş, bağla
                    return; // İşlem bitti, çık
                }
                temp = temp.left; // Dolu, sola ilerle
            }
            else if (comparison > 0) {
                // SAĞA GİT (Yeni gelen daha büyük/sonra)
                if (temp.right == null) {
                    temp.right = newNode; // Yer boş, bağla
                    return; // İşlem bitti, çık
                }
                temp = temp.right; // Dolu, sağa ilerle
            }
            else {
                // EŞİTSE (Aynı isimde kayıt var)
                // İstersen burada uyarı verebilirsin veya hiçbir şey yapmazsın.
                System.out.println("Bu kayıt zaten mevcut.");
                return;
            }

            /*if (comparison > 0) {
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
            }*/
        }
    }
    public void search(T name) {
        if(isEmpty()) {
            System.out.println("Patient List is Empty!");
            return;
        }
        BSTNode<T> temp = root;
        while(true){
            int comparison = name.compareTo(temp.data);
            if(comparison == 0) {
                System.out.println("User Has Found!");
                System.out.println(temp.data.toString());
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
