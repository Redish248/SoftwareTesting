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
        when(sinus.calc(0)).thenReturn(Math.sin(0));
        when(sinus.calc(-delta)).thenReturn(Math.sin(-delta));
        when(sinus.calc(-Math.PI/2+delta)).thenReturn(Math.sin(-Math.PI/2+delta));
        when(sinus.calc(-Math.PI/2)).thenReturn(Math.sin(-Math.PI/2));
        when(sinus.calc(-Math.PI/2-delta)).thenReturn(Math.sin(-Math.PI/2-delta));
        when(sinus.calc(-2)).thenReturn(Math.sin(-2));
        when(sinus.calc(-Math.PI+delta)).thenReturn(Math.sin(-Math.PI+delta));
        when(sinus.calc(-Math.PI)).thenReturn(Math.sin(-Math.PI));
        when(sinus.calc(-Math.PI-delta)).thenReturn(Math.sin(-Math.PI-delta));
        when(sinus.calc(-4)).thenReturn(Math.sin(-4));
        when(sinus.calc(-3*Math.PI/2+delta)).thenReturn(Math.sin(-3*Math.PI/2+delta));
        when(sinus.calc(-3*Math.PI/2)).thenReturn(Math.sin(-3*Math.PI/2));
        when(sinus.calc(-3*Math.PI/2-delta)).thenReturn(Math.sin(-3*Math.PI/2-delta));
        when(sinus.calc(-5)).thenReturn(Math.sin(-5));
        when(sinus.calc(-2*Math.PI+delta)).thenReturn(Math.sin(-2*Math.PI+delta));
        when(sinus.calc(-2*Math.PI)).thenReturn(Math.sin(-2*Math.PI));
        when(sinus.calc(-2*Math.PI-delta)).thenReturn(Math.sin(-2*Math.PI-delta));
        when(sinus.calc(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(sinus.calc(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());
        cosine = new Cosine(sinus);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1E-6, -Math.PI/2 + 1E-6, -Math.PI/2, -Math.PI/2 - 1E-6, -2, -Math.PI + 1E-6,
            -Math.PI, -Math.PI - 1E-6, -4, -3*Math.PI/2 + 1E-6, -3*Math.PI/2, -3*Math.PI/2 - 1E-6, -5,
            -2*Math.PI + 1E-6, -2*Math.PI, -2*Math.PI - 1E-6})
    public void testNegativePeriod(double x) {
        assertEquals(Math.cos(x), cosine.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cosine.calc(x));
    }
}
