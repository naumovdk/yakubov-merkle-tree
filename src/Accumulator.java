public interface Accumulator {
    boolean verify(byte[] value,Proof proof);

    Proof add(byte[] value, User author);
}
