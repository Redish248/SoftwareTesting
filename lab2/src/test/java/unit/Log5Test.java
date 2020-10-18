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
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-10);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-5);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(0);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-DELTA);
        when(lnFunction.ln(DELTA)).thenReturn(Math.log(DELTA));
        when(lnFunction.ln(1)).thenReturn(Math.log(1));
        when(lnFunction.ln(1+DELTA)).thenReturn(Math.log(1+DELTA));
        when(lnFunction.ln(1-DELTA)).thenReturn(Math.log(1-DELTA));
        when(lnFunction.ln(5)).thenReturn(Math.log(5));
        when(lnFunction.ln(0.5)).thenReturn(Math.log(0.5));
        when(lnFunction.ln(10)).thenReturn(Math.log(10));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-5, -10})
    public void testNegative(double x) {
        assertThrows(IllegalArgumentException.class, () -> log5.log5(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -DELTA})
    public void testZero(double x) {
        assertThrows(IllegalArgumentException.class, () -> log5.log5(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {DELTA, 1, 1+DELTA, 1-DELTA})
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
