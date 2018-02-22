package ale.server.ale.calculations;

class Node {
    private String value;
    private Node left;
    private Node right;

    Node(String value) {
        this.value = value;
        right = null;
        left = null;
    }

    public void setKey(String value) {
        this.value = value;
    }

    public String getKey() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }
}