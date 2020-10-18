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
        when(sinus.calc(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(sinus.calc(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());

        Cosine cosine = mock(Cosine.class);
        when(cosine.calc(0)).thenReturn(Math.cos(2));
        when(cosine.calc(-delta)).thenReturn(Math.cos(-delta));
        when(cosine.calc(-Math.PI/2+delta)).thenReturn(Math.cos(-Math.PI/2+delta));
        when(cosine.calc(-Math.PI/2)).thenReturn(Math.cos(-Math.PI/2));
        when(cosine.calc(-Math.PI/2-delta)).thenReturn(Math.cos(-Math.PI/2-delta));
        when(cosine.calc(-2)).thenReturn(Math.cos(-2));
        when(cosine.calc(-Math.PI+delta)).thenReturn(Math.cos(-Math.PI+delta));
        when(cosine.calc(-Math.PI)).thenReturn(Math.cos(-Math.PI));
        when(cosine.calc(-Math.PI-delta)).thenReturn(Math.cos(-Math.PI-delta));
        when(cosine.calc(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(cosine.calc(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());

        tangens = new Tangens(sinus, cosine);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1E-6, -Math.PI/2 + 1E-6, -Math.PI/2, -Math.PI/2 - 1E-6, -2, -Math.PI + 1E-6,
            -Math.PI, -Math.PI - 1E-6})
    public void testNegativePeriod(double x) {
        assertEquals(Math.tan(x), tangens.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> tangens.calc(x));
    }
}
