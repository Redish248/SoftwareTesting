package integration;

import functions.LnFunction;
import functions.Log2;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Log2Test_lvl1 {

    private final LnFunction lnFunction = new LnFunction();
    private final Log2 log2 = new Log2(lnFunction);
    private final double DELTA = 1E-6;

    @ParameterizedTest
    @ValueSource(doubles = {-DELTA, -5, -10})
    public void testNegative(double x) {
        assertThrows(IllegalArgumentException.class, () -> log2.log2(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0})
    public void testZero(double x) {
        assertThrows(IllegalArgumentException.class, () -> log2.log2(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 1, 1+DELTA, 1-DELTA})
    public void testOne(double x) {
        assertEquals(log2(x), log2.log2(x), DELTA);
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(log2(0.5), log2.log2(0.5), DELTA);
    }

    @Test
    public void testAfterOne() {
        assertEquals(log2(10), log2.log2(10), DELTA);
    }

    @Ignore
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

}
