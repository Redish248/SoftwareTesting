package integration;

import functions.Cosine;
import functions.Sinus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class CosTest {
    private Cosine cosine;
    private double delta;

    @BeforeEach
    public void init() {
        delta = 1E-5;
        cosine = new Cosine(new Sinus());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1E-6, -Math.PI/2 + 1E-6, -Math.PI/2, -Math.PI/2 - 1E-6, -2, -Math.PI + 1E-6,
            -Math.PI, -Math.PI - 1E-6, -4, -3*Math.PI/2 + 1E-6, -3*Math.PI/2, -3*Math.PI/2 - 1E-6, -5,
            -2*Math.PI + 1E-6, -2*Math.PI, -2*Math.PI - 1E-6})
    public void testNegativePeriod(double x) {
        //чекнуть точность возле -3Pi/2
        assertEquals(Math.cos(x), cosine.calc(x), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cosine.calc(x));
    }
}
