package unit;

import functions.LnFunction;
import functions.Log2;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Log2Test {

    LnFunction lnFunction = mock(LnFunction.class);
    private final Log2 log2 = new Log2(lnFunction);
    private final double DELTA = 1E-6;

    {
        doThrow(new IllegalArgumentException()).when(lnFunction).calc(-10);
        doThrow(new IllegalArgumentException()).when(lnFunction).calc(-5);
        doThrow(new IllegalArgumentException()).when(lnFunction).calc(0);
        doThrow(new IllegalArgumentException()).when(lnFunction).calc(-DELTA);
        when(lnFunction.calc(DELTA)).thenReturn(-13.8155106);
        when(lnFunction.calc(1)).thenReturn(0.0);
        when(lnFunction.calc(2)).thenReturn(0.6931472);
        when(lnFunction.calc(1+DELTA)).thenReturn(0.000001);
        when(lnFunction.calc(1-DELTA)).thenReturn(-0.000001);
        when(lnFunction.calc(5)).thenReturn(1.6094379);
        when(lnFunction.calc(0.5)).thenReturn(-0.6931472);
        when(lnFunction.calc(10)).thenReturn(2.3025851);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-5, -10})
    public void testNegative(double x) {
        assertThrows(IllegalArgumentException.class, () -> log2.calc(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-DELTA, 0})
    public void testZero(double x) {
        assertThrows(IllegalArgumentException.class, () -> log2.calc(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {DELTA, 1, 1+DELTA, 1-DELTA})
    public void testOne(double x) {
        assertEquals(log2(x), log2.calc(x), DELTA);
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(log2(0.5), log2.calc(0.5), DELTA);
    }

    @Test
    public void testAfterOne() {
        assertEquals(log2(10), log2.calc(10), DELTA);
    }

    @Ignore
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

}
