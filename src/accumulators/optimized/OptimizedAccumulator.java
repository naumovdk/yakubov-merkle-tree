package accumulators.optimized;

import accumulators.Accumulator;
import accumulators.User;
import accumulators.Proof;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimizedAccumulator implements Accumulator {
    private List<Node> roots = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    private Map<Node, User> authors = new HashMap<>();

    public OptimizedAccumulator() {}

    @Override
    public boolean verify(byte[] value, Proof proof) {

    }

    @Override
    public Proof add(byte[] value, User author) {
        Node node = new Node(value);
        nodes.add(node);
        authors.put(node, author);
        Proof proof = new Proof(nodes.size() - 1);
        int i = 0;
        for (; i < roots.size() && roots.get(i) != null; i++) {
            update(node, node.getHash());
            node = new Node(roots.get(i), node);
            proof.add(roots.get(i).getHash());
        }
        if (i >= roots.size()) {
            roots.add(node);
        } else {
            roots.add(i, node);
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
