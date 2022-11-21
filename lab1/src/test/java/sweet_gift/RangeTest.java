package sweet_gift;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RangeTest {
    @Test
    public void testIn() {
        Range r = new Range(5, 10);

        assertTrue(r.in(5));
        assertTrue(r.in(9));

        assertFalse(r.in(4));
        assertFalse(r.in(10));
    }
}
