package accumulators.basic;

import accumulators.Accumulator;
import accumulators.Proof;
import accumulators.User;
import accumulators.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MerkleTree implements Accumulator {
    private List<User> users;
    private Node head;


    @Override
    public boolean verify(byte[] value, Proof proof) {
        return false;
    }

    @Override
    public Proof add(byte[] value, User author) {
        var hashedValue = Utils.generateValueHash(value);
        return null;
    }
}
