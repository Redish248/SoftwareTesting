package unit;

import functions.Cosine;
import functions.Sinus;
import functions.Tangens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TgTest {
    private Tangens tangens;
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

        Cosine cosine = mock(Cosine.class);
        when(cosine.cos(0)).thenReturn(Math.cos(2));
        when(cosine.cos(-delta)).thenReturn(Math.cos(-delta));
        when(cosine.cos(-Math.PI/2+delta)).thenReturn(Math.cos(-Math.PI/2+delta));
        when(cosine.cos(-Math.PI/2)).thenReturn(Math.cos(-Math.PI/2));
        when(cosine.cos(-Math.PI/2-delta)).thenReturn(Math.cos(-Math.PI/2-delta));
        when(cosine.cos(-Math.PI+delta)).thenReturn(Math.cos(-Math.PI+delta));
        when(cosine.cos(-Math.PI)).thenReturn(Math.cos(-Math.PI));
        when(cosine.cos(-Math.PI-delta)).thenReturn(Math.cos(-Math.PI-delta));
        when(cosine.cos(-3*Math.PI/2+delta)).thenReturn(Math.cos(-3*Math.PI/2+delta));
        when(cosine.cos(-3*Math.PI/2)).thenReturn(Math.cos(-3*Math.PI/2));
        when(cosine.cos(-3*Math.PI/2-delta)).thenReturn(Math.cos(-3*Math.PI/2-delta));
        when(cosine.cos(-2*Math.PI+delta)).thenReturn(Math.cos(-2*Math.PI+delta));
        when(cosine.cos(-2*Math.PI)).thenReturn(Math.cos(-2*Math.PI));
        when(cosine.cos(-2*Math.PI-delta)).thenReturn(Math.cos(-2*Math.PI-delta));
        when(cosine.cos(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(cosine.cos(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());

        tangens = new Tangens(sinus, cosine);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -10E-6, -Math.PI/2 + 10E-6, -Math.PI/2, -Math.PI/2 - 10E-6, -Math.PI + 10E-6, -Math.PI,
            -Math.PI - 10E-6, -3*Math.PI/2 + 10E-6, -3*Math.PI/2, -3*Math.PI/2 - 10E-6, -2*Math.PI + 10E-6, -2*Math.PI, -2*Math.PI - 10E-6})
    public void testNegativePeriod(double x) {
        assertEquals(Math.tan(x), tangens.tg(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> tangens.tg(x));
    }
}
