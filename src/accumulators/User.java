package accumulators;

public class User {
    private Proof proof;

    public User() {
        proof = new Proof(90);
    }

    public void sendProof(Proof proof) {
        this.proof = proof;
    }

    public void acceptUpdate(byte[] update) {
        proof.add(update);
    }

    public Proof getProof() {
        return proof;
    }
}
