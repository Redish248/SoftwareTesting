package integration;

import functions.Cosine;
import functions.Sinus;
import functions.Tangens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class TgTest {
    private Tangens tangens;
    private double delta;

    @BeforeEach
    public void mockFunctions() {
        delta = 1E-6;
        Sinus sinus = new Sinus();
        Cosine cosine = new Cosine(sinus);
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
