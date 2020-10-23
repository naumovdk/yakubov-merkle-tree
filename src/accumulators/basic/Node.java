package accumulators.basic;

import accumulators.Proof;
import accumulators.User;
import accumulators.Utils;

public class Node {
    private byte[] hash;
    private Node left = null;
    private Node right = null;
    private Node ancestor = null;
    private User author = null;

    public void setAuthor(User author) {
        this.author = author;
    }

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

    public void setHash(byte[] hash) {
        this.hash = hash;
        if (ancestor != null) {
            ancestor.recalculateHash();
        }
    }

    private void recalculateHash() {
        hash = Utils.generateNodeHash(left.getHash(), right.getHash());
        if (ancestor != null) {
            ancestor.recalculateHash();
        } else {
            sendProof(new Proof(0));
        }
    }

    private void sendProof(Proof proof) {
        if (isLeaf()) {
            if (author != null) {
                author.acceptProof(proof);
            }
        } else {
            var proofForLeft = new Proof(proof);
            var proofForRight = new Proof(proof);
            proofForLeft.add(right.getHash());
            proofForRight.add(left.getHash());
            left.sendProof(proofForLeft);
            right.sendProof(proofForRight);
        }
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

    Node getAncestor() {
        return ancestor;
    }

}
