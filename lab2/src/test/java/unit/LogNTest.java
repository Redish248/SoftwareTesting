package unit;

import functions.LnFunction;
import functions.Log2;
import functions.Log5;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogNTest {

    LnFunction lnFunction;
    private final Log2 log2;
    private final Log5 log5;

    {
        lnFunction = mock(LnFunction.class);
        log2 = new Log2(lnFunction);
        log5 = new Log5(lnFunction);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-10);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-5);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(-1);
        doThrow(new IllegalArgumentException()).when(lnFunction).ln(0);
        when(lnFunction.ln(1)).thenReturn(Math.log(1));
        when(lnFunction.ln(2)).thenReturn(Math.log(2));
        when(lnFunction.ln(0.5)).thenReturn(Math.log(0.5));
        when(lnFunction.ln(5)).thenReturn(Math.log(5));
        when(lnFunction.ln(10)).thenReturn(Math.log(10));
    }

    @Test
    public void test2Negative() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> log2.log2(-10)),
                () -> assertThrows(IllegalArgumentException.class, () -> log2.log2(-5)),
                () -> assertThrows(IllegalArgumentException.class, () -> log2.log2(-1))
        );
    }

    @Test
    public void test2Zero() {
        assertThrows(IllegalArgumentException.class, () -> log2.log2(0));
    }

    @Test
    public void test2One() {
        assertEquals(log2(1), log2.log2(1), 10E-6);
    }

    @Test
    public void test2BetweenZeroAndOne() {
        assertEquals(log2(0.5), log2.log2(0.5), 10E-6);
    }

    @Test
    public void test2AfterOne() {
        assertEquals(log2(5), log2.log2(5), 10E-6);
    }

    @Test
    public void test5Negative() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> log5.log5(-10)),
                () -> assertThrows(IllegalArgumentException.class, () -> log5.log5(-5)),
                () -> assertThrows(IllegalArgumentException.class, () -> log5.log5(-1))
        );
    }

    @Test
    public void test5Zero() {
        assertThrows(IllegalArgumentException.class, () -> log5.log5(0));
    }

    @Test
    public void test5One() {
        assertEquals(log5(1), log5.log5(1), 10E-6);
    }

    @Test
    public void test5BetweenZeroAndOne() {
        assertEquals(log5(0.5), log5.log5(0.5), 10E-6);
    }

    @Test
    public void test5AfterOne() {
        assertEquals(log5(10), log5.log5(10), 10E-6);
    }

    @Ignore
    private double log5(double x) {
        return Math.log(x) / Math.log(5);
    }

    @Ignore
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

}
