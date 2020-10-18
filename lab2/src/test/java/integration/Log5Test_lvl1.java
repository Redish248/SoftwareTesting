package integration;

import functions.LnFunction;
import functions.Log5;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Log5Test_lvl1 {
    private final LnFunction lnFunction = new LnFunction();
    private final Log5 log5 = new Log5(lnFunction);
    private final double DELTA = 1E-6;

    @ParameterizedTest
    @ValueSource(doubles = {-1, -5, -10})
    public void testNegative(double x) {
        assertThrows(IllegalArgumentException.class, () -> log5.log5(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -DELTA})
    public void testZero(double x) {
        assertThrows(IllegalArgumentException.class, () -> log5.log5(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 1, 1+DELTA, 1-DELTA})
    public void testOne(double x) {
        assertEquals(log5(x), log5.log5(x), DELTA);
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(log5(0.5), log5.log5(0.5), DELTA);
    }

    @Test
    public void testAfterOne() {
        assertEquals(log5(10), log5.log5(10), DELTA);
    }

    @Ignore
    private double log5(double x) {
        return Math.log(x) / Math.log(5);
    }

}
