package unit;

import functions.LnFunction;
import functions.Log2;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Log2Test {

    LnFunction lnFunction = mock(LnFunction.class);
    private final Log2 log2 = new Log2(lnFunction);
    private final double DELTA = 10E-6;

    {
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-10);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-5);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-1);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(0);
        when(lnFunction.ln(1)).thenReturn(Math.log(1));
        when(lnFunction.ln(2)).thenReturn(Math.log(2));
        when(lnFunction.ln(0.5)).thenReturn(Math.log(0.5));
        when(lnFunction.ln(5)).thenReturn(Math.log(5));
    }

    @Test
    public void testNegative() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> log2.log2(-10)),
                () -> assertThrows(IllegalArgumentException.class, () -> log2.log2(-5)),
                () -> assertThrows(IllegalArgumentException.class, () -> log2.log2(-1))
        );
    }

    @Test
    public void testZero() {
        assertThrows(IllegalArgumentException.class, () -> log2.log2(0));
    }

    @Test
    public void testOne() {
        assertEquals(log2(1), log2.log2(1), DELTA);
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(log2(0.5), log2.log2(0.5), DELTA);
    }

    @Test
    public void testAfterOne() {
        assertEquals(log2(5), log2.log2(5), DELTA);
    }

    @Ignore
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

}
