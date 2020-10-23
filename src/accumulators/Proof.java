package accumulators;

import java.util.ArrayList;

public class Proof extends ArrayList<byte[]> {
    private int index;

    public Proof(int index) {
        this.index = index;
    }


    public int getIndex() {
        return index;
    }
}
