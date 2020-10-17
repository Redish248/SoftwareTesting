package unit;

import functions.LnFunction;
import functions.Log5;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Log5Test {
    LnFunction lnFunction;
    private final Log5 log5;

    {
        lnFunction = mock(LnFunction.class);
        log5 = new Log5(lnFunction);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-10);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-5);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-1);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(0);
        when(lnFunction.ln(1)).thenReturn(Math.log(1));
        when(lnFunction.ln(5)).thenReturn(Math.log(5));
        when(lnFunction.ln(0.5)).thenReturn(Math.log(0.5));
        when(lnFunction.ln(10)).thenReturn(Math.log(10));
    }

    @Test
    public void testNegative() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> log5.log5(-10)),
                () -> assertThrows(IllegalArgumentException.class, () -> log5.log5(-5)),
                () -> assertThrows(IllegalArgumentException.class, () -> log5.log5(-1))
        );
    }

    @Test
    public void testZero() {
        assertThrows(IllegalArgumentException.class, () -> log5.log5(0));
    }

    @Test
    public void testOne() {
        assertEquals(log5(1), log5.log5(1), 10E-6);
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(log5(0.5), log5.log5(0.5), 10E-6);
    }

    @Test
    public void testAfterOne() {
        assertEquals(log5(10), log5.log5(10), 10E-6);
    }

    @Ignore
    private double log5(double x) {
        return Math.log(x) / Math.log(5);
    }

}
