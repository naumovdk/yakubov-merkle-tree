package accumulators;

public interface Accumulator {
    public boolean verify(byte[] value, Proof proof);

    public Proof add(byte[] value, User author);
}
