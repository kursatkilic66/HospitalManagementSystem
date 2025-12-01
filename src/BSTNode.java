package src;

public class BSTNode {
    private Patient data;
    BSTNode left,right;

    public BSTNode(Patient data) {
        this.data = data;
    }
    public Patient getData() {
        return data;
    }

    public void setData(Patient data) {
        this.data = data;
    }
}
