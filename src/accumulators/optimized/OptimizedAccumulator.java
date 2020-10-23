package accumulators.optimized;

import accumulators.Accumulator;
import accumulators.User;
import accumulators.Proof;

public class OptimizedAccumulator implements Accumulator {
    private 

    @Override
    public boolean verify(byte[] value, Proof proof) {
        return false;
    }

    @Override
    public Proof add(byte[] value, User author) {
        return null;
    }
}
