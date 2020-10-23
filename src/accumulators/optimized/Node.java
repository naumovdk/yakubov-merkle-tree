package accumulators.optimized;

import accumulators.Utils;

class Node {
    private byte[] hash;
    private Node left = null;
    private Node right = null;
    private Node ancestor = null;

    Node(byte[] value) {
        this.hash = Utils.generateValueHash(value);
    }

    Node(Node left, Node right) {
        this.hash = Utils.generateNodeHash(left.getHash(), right.getHash());
        this.left = left;
        left.ancestor = this;
        this.right = right;
        right.ancestor = this;
    }

    boolean isLeaf() {
        return left == null && right == null;
    }

    Node getLeft() {
        return left;
    }

    byte[] getHash() {
        return hash;
    }

    Node getRight() {
        return right;
    }
}
