package accumulators.optimized;

class Node {
    private byte[] hash;
    private Node left;
    private Node right;

    Node(byte[] hash, Node left, Node right) {
        this.hash = hash;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public byte[] getHash() {
        return hash;
    }

    public Node getRight() {
        return right;
    }
}
