package unit;

import functions.LnFunction;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class LnTest {

    private final LnFunction lnFunction = new LnFunction();
    private final double DELTA = 1E-6;

    @ParameterizedTest
    @ValueSource(doubles = {-DELTA, -1, -5, -10})
    public void testNegative(double x) {
        assertThrows(IllegalArgumentException.class, () -> lnFunction.calc(x));
    }

    @Test
    public void testZero() {
        assertThrows(IllegalArgumentException.class, () -> lnFunction.calc(0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 1, 1 - DELTA, 1 + DELTA})
    public void testOne(double x) {
        assertEquals(Math.log(x), lnFunction.calc(x), DELTA);
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(Math.log(0.5), lnFunction.calc(0.5), DELTA);
    }

    @Test
    public void testBetweenOneAndInfinity() {
        assertEquals(Math.log(5), lnFunction.calc(5), DELTA);
    }

    @Test
    public void testRaiseBetweenZeroAndOne() {
        assertAll(
                () -> assertTrue(lnFunction.calc(0.1) < lnFunction.calc(0.5)),
                () -> assertTrue(lnFunction.calc(0.5) < lnFunction.calc(0.7)),
                () -> assertTrue(lnFunction.calc(0.7) < lnFunction.calc(1))
        );
    }

    @Test
    public void testRaiseBetweenOneAndInfinity() {
        assertAll(
                () -> assertTrue(lnFunction.calc(1) < lnFunction.calc(2)),
                () -> assertTrue(lnFunction.calc(2) < lnFunction.calc(5)),
                () -> assertTrue(lnFunction.calc(5) < lnFunction.calc(10))
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    public void testIncorrectArguments(double x) {
        assertThrows(IllegalArgumentException.class, () -> lnFunction.calc(x));
    }

}
