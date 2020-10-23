package accumulators.optimized;

import accumulators.Accumulator;
import accumulators.User;
import accumulators.Proof;
import accumulators.Utils;

import java.util.*;

public class OptimizedAccumulator implements Accumulator {
    private List<Node> roots = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    private Map<Node, User> authors = new HashMap<>();

    public OptimizedAccumulator() {
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
        for (byte[] hash : proof) {
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
    public Proof add(byte[] value, User author) {
        Node node = new Node(value);
        nodes.add(node);
        authors.put(node, author);
        Proof proof = new Proof(nodes.size() - 1);
        author.sendProof(proof);
        int i = 0;
        for (; i < roots.size() && roots.get(i) != null; i++) {
            update(node, roots.get(i).getHash());
            update(roots.get(i), node.getHash());
            node = new Node(roots.get(i), node);
            //proof.add(roots.get(i).getHash());
            roots.set(i, null);
        }
        if (i >= roots.size()) {
            roots.add(node);
        } else {
            roots.set(i, node);
        }
        return proof;
    }

    private void update(Node node, byte[] update) {
        if (node.isLeaf()) {
            authors.get(node).acceptUpdate(update);
        } else {
            update(node.getLeft(), update);
            update(node.getRight(), update);
        }
    }
}
