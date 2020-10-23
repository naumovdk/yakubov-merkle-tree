package accumulators.basic;

import accumulators.Accumulator;
import accumulators.Proof;
import accumulators.User;
import accumulators.Utils;

import java.util.*;

public class MerkleTree implements Accumulator {
    private List<Node> nodes = new ArrayList<>();
    private int size = 0;
    private Node head;

    public MerkleTree() {
        this.nodes = new ArrayList<>();
        nodes.add(new Node(null));
        head = nodes.get(0);
    }

    @Override
    public boolean verify(byte[] value, Proof proof) {
        if (value == null || proof == null)
            return false;
        int id = proof.getIndex();
        if (id > nodes.size()) {
            return false;
        }
        byte[] currentHash = Utils.generateValueHash(value);
        Node cur = nodes.get(id);
        for (int i = 0; i < proof.size(); i++) {
            byte[] hash = proof.get(proof.size() - 1 - i);
            Node parent = cur.getAncestor();
            if (parent == null) {
                return false;
            }
            if (parent.getLeft() == cur) {
                currentHash = Utils.generateNodeHash(currentHash, hash);
            } else {
                currentHash = Utils.generateNodeHash(hash, currentHash);
            }
            cur = parent;
        }
        return cur.getAncestor() == null && Arrays.equals(currentHash, cur.getHash());
    }

    @Override
    public void add(byte[] value, User author) {
        if (size < nodes.size()) {
            nodes.get(size).setAuthor(author);
            nodes.get(size).setHash(Utils.generateValueHash(value));
            size++;
        } else {
            Queue<Node> queue = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                nodes.add(new Node(null));
                queue.add(nodes.get(nodes.size() - 1));
            }
            while (queue.size() > 1) {
                Node left = queue.poll();
                Node right = queue.poll();
                queue.add(new Node(left, right));
            }
            Node rightNode = queue.poll();
            head = new Node(head, rightNode);
            add(value, author);
        }
    }
}
