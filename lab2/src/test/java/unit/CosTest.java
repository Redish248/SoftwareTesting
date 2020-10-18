package unit;

import functions.Cosine;
import functions.Sinus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CosTest {
    private Cosine cosine;
    private double delta;

    @BeforeEach
    public void mockFunctions() {
        delta = 1E-6;
        Sinus sinus = mock(Sinus.class);

        when(sinus.calc(0)).thenReturn(0.0);
        when(sinus.calc(-1e-06)).thenReturn(-9.999999999998333e-07);
        when(sinus.calc(-1.5707953267948966)).thenReturn(-0.9999999999995);
        when(sinus.calc(-1.5707963267948966)).thenReturn(-1.0);
        when(sinus.calc(-1.5707973267948965)).thenReturn(-0.9999999999995);
        when(sinus.calc(-2)).thenReturn(-0.9092974268256817);
        when(sinus.calc(-3.141591653589793)).thenReturn(-1.000000000262076e-06);
        when(sinus.calc(-3.141592653589793)).thenReturn(-1.2246467991473532e-16);
        when(sinus.calc(-3.1415936535897933)).thenReturn(1.0000000000171467e-06);
        when(sinus.calc(-4)).thenReturn(0.7568024953079282);
        when(sinus.calc(-4.7123879803846895)).thenReturn(0.9999999999995);
        when(sinus.calc(-4.71238898038469)).thenReturn(1.0);
        when(sinus.calc(-4.71238998038469)).thenReturn(0.9999999999995);
        when(sinus.calc(-5)).thenReturn(0.9589242746631385);
        when(sinus.calc(-6.283184307179586)).thenReturn(1.0000000003845406e-06);
        when(sinus.calc(-6.283185307179586)).thenReturn(2.4492935982947064e-16);
        when(sinus.calc(-6.283186307179586)).thenReturn(-9.99999999894682e-07);
        when(sinus.calc(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(sinus.calc(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());
        cosine = new Cosine(sinus);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1e-06, -1.5707953267948966, -1.5707963267948966, -1.5707973267948965, -2,
            -3.141591653589793, -3.141592653589793, -3.1415936535897933, -4, -4.7123879803846895, -4.71238898038469,
            -4.71238998038469, -5, -6.283184307179586, -6.283185307179586, -6.283186307179586})
    public void testNegativePeriod(double x) {
        assertEquals(Math.cos(x), cosine.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cosine.calc(x));
    }
}
