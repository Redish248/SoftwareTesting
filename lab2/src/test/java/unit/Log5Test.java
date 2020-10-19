package unit;

import functions.LnFunction;
import functions.Log5;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Log5Test {
    private final LnFunction lnFunction = mock(LnFunction.class);
    private final Log5 log5 = new Log5(lnFunction);
    private final double DELTA = 1E-6;

    @BeforeEach
    public void createMock(){
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
        when(lnFunction.calc(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(lnFunction.calc(Double.POSITIVE_INFINITY)).thenThrow(new IllegalArgumentException());
        when(lnFunction.calc(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-5, -10})
    public void testNegative(double x) {
        assertThrows(IllegalArgumentException.class, () -> log5.calc(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -DELTA})
    public void testZero(double x) {
        assertThrows(IllegalArgumentException.class, () -> log5.calc(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {DELTA, 1, 1+DELTA, 1-DELTA})
    public void testOne(double x) {
        assertEquals(log5(x), log5.calc(x), DELTA);
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(log5(0.5), log5.calc(0.5), DELTA);
    }

    @Test
    public void testAfterOne() {
        assertEquals(log5(10), log5.calc(10), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        assertThrows(IllegalArgumentException.class, () -> log5.calc(x));
    }

    @Ignore
    private double log5(double x) {
        return Math.log(x) / Math.log(5);
    }

}
