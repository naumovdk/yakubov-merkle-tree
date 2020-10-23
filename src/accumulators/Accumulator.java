package accumulators;

public interface Accumulator {
    public boolean verify(byte[] value, Proof proof);

    public void add(byte[] value, User author);
}
