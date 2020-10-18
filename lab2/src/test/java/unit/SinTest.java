package unit;

import functions.Sinus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class SinTest {
    private Sinus sinus;
    private double delta;

    @BeforeEach
    public void mockFunctions() {
        sinus = new Sinus();
        delta = 10E-6;
    }


    @ParameterizedTest
    @ValueSource(doubles = {0, -10E-6, -Math.PI/2 + 10E-6, -Math.PI/2, -Math.PI/2 - 10E-6, -2, -Math.PI + 10E-6,
            -Math.PI, -Math.PI - 10E-6, -4, -3*Math.PI/2 + 10E-6, -3*Math.PI/2, -3*Math.PI/2 - 10E-6, -5,
            -2*Math.PI + 10E-6, -2*Math.PI, -2*Math.PI - 10E-6})
    public void testNegativePeriod(double x) {
        assertEquals(Math.sin(x), sinus.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sinus.calc(x));
    }
}
