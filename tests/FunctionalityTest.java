import accumulators.Accumulator;
import accumulators.Proof;
import accumulators.User;
import accumulators.optimized.OptimizedAccumulator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FunctionalityTest {
    private Accumulator accumulatorForTest = new OptimizedAccumulator();

    @Test
    public void testBasic() {
        byte[] test = {1, 2, 3, 4, 5};
        byte[] test2 = {1, 5, 3, 4, 5};
        byte[] test3 = {1, 12, 3, 4, 5};

        User user = new User();
        User user2 = new User();
        Proof proof = accumulatorForTest.add(test, user);
       // user.sendProof(proof);
        accumulatorForTest.add(test2, user2);
      //  user2.sendProof(new Proof(12));
        accumulatorForTest.add(test2, user2);
     //   user2.sendProof(new Proof(34));
        Assert.assertTrue(accumulatorForTest.verify(test, user.getProof()));
    }
}
