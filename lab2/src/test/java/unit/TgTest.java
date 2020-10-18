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
        when(sinus.calc(0)).thenReturn(0.0);
        when(sinus.calc(-delta)).thenReturn(-0.00000099);
        when(sinus.calc(-1.5707953267948966)).thenReturn(-0.9999999999995);
        when(sinus.calc(-1.5707973267948965)).thenReturn(-0.9999999999995);
        when(sinus.calc(-2)).thenReturn(-0.9092974);
        when(sinus.calc(-3.141591653589793)).thenReturn(-0.000001);
        when(sinus.calc(-3.141592653589793)).thenReturn(0.0);
        when(sinus.calc(-3.1415936535897933)).thenReturn(0.000001);
        when(sinus.calc(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(sinus.calc(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());

        Cosine cosine = mock(Cosine.class);
        when(cosine.calc(0)).thenReturn(1.0);
        when(cosine.calc(-1e-06)).thenReturn(0.9999999999995);
        when(cosine.calc(-1.5707953267948966)).thenReturn(9.99999999978799e-07);
        when(cosine.calc(-1.5707973267948965)).thenReturn(-9.999999998563343e-07);
        when(cosine.calc(-2)).thenReturn(-0.4161468365471424);
        when(cosine.calc(-3.141591653589793)).thenReturn(-0.9999999999995);
        when(cosine.calc(-3.141592653589793)).thenReturn(-1.0);
        when(cosine.calc(-3.1415936535897933)).thenReturn(-0.9999999999995);
        when(cosine.calc(Double.NaN)).thenThrow(new IllegalArgumentException());
        when(cosine.calc(Double.NEGATIVE_INFINITY)).thenThrow(new IllegalArgumentException());

        tangens = new Tangens(sinus, cosine);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1e-06, -1.5707953267948966, -1.5707973267948965, -2,
            -3.141591653589793, -3.141592653589793, -3.1415936535897933})
    public void testNegativePeriod(double x) {
        assertEquals(Math.tan(x), tangens.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> tangens.calc(x));
    }
}
