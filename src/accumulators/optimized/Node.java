package accumulators.optimized;

import accumulators.Utils;

class Node {
    private byte[] hash;
    private Node left = null;
    private Node right = null;

    Node(byte[] value) {
        this.hash = Utils.generateValueHash(value);
    }

    Node(Node left, Node right) {
        this.hash = Utils.generateNodeHash(left.getHash(), right.getHash());
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
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
