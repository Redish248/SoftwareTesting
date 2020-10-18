package unit;

import functions.Cosine;
import functions.Sinus;
import org.junit.Before;
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
        delta = 10E-6;
        Sinus sinus = mock(Sinus.class);
        when(sinus.sin(0)).thenReturn(Math.sin(0));
        when(sinus.sin(-delta)).thenReturn(Math.sin(-delta));
        when(sinus.sin(-Math.PI/2+delta)).thenReturn(Math.sin(-Math.PI/2+delta));
        when(sinus.sin(-Math.PI/2)).thenReturn(Math.sin(-Math.PI/2));
        when(sinus.sin(-Math.PI/2-delta)).thenReturn(Math.sin(-Math.PI/2-delta));
        when(sinus.sin(-Math.PI+delta)).thenReturn(Math.sin(-Math.PI+delta));
        when(sinus.sin(-Math.PI)).thenReturn(Math.sin(-Math.PI));
        when(sinus.sin(-Math.PI-delta)).thenReturn(Math.sin(-Math.PI-delta));
        when(sinus.sin(-3*Math.PI/2+delta)).thenReturn(Math.sin(-3*Math.PI/2+delta));
        when(sinus.sin(-3*Math.PI/2)).thenReturn(Math.sin(-3*Math.PI/2));
        when(sinus.sin(-3*Math.PI/2-delta)).thenReturn(Math.sin(-3*Math.PI/2-delta));
        when(sinus.sin(-2*Math.PI+delta)).thenReturn(Math.sin(-2*Math.PI+delta));
        when(sinus.sin(-2*Math.PI)).thenReturn(Math.sin(-2*Math.PI));
        when(sinus.sin(-2*Math.PI-delta)).thenReturn(Math.sin(-2*Math.PI-delta));
        when(sinus.sin(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(sinus.sin(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());
        cosine = new Cosine(sinus);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -10E-6, -Math.PI/2 + 10E-6, -Math.PI/2, -Math.PI/2 - 10E-6, -Math.PI + 10E-6, -Math.PI,
            -Math.PI - 10E-6, -3*Math.PI/2 + 10E-6, -3*Math.PI/2, -3*Math.PI/2 - 10E-6, -2*Math.PI + 10E-6, -2*Math.PI, -2*Math.PI - 10E-6})
    public void testNegativePeriod(double x) {
        assertEquals(Math.cos(x), cosine.cos(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cosine.cos(x));
    }
}
