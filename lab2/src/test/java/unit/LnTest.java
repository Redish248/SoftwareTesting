package unit;

import functions.LnFunction;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class LnTest {

    private final LnFunction lnFunction;

    @Test
    public void testNegative() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> lnFunction.ln(-10)),
                () -> assertThrows(IllegalArgumentException.class, () -> lnFunction.ln(-5)),
                () -> assertThrows(IllegalArgumentException.class, () -> lnFunction.ln(-1))
        );
    }

    @Test
    public void testZero() {
        assertThrows(IllegalArgumentException.class, () -> lnFunction.ln(0));
    }

    @Test
    public void testOne() {
        assertEquals(Math.log(1), lnFunction.ln(1));
    }

    @Test
    public void testBetweenZeroAndOne() {
        assertEquals(Math.log(0.5), lnFunction.ln(0.5), 10E-6);
    }

    @Test
    public void testRaiseBetweenZeroAndOne() {
        assertAll(
                () -> assertTrue(lnFunction.ln(0.1) < lnFunction.ln(0.5)),
                () -> assertTrue(lnFunction.ln(0.5) < lnFunction.ln(0.7)),
                () -> assertTrue(lnFunction.ln(0.7) < lnFunction.ln(1))
        );
    }

    @Test
    public void testBetweenOneAndInfinity() {
        assertEquals(Math.log(5), lnFunction.ln(5), 10E-6);
    }

    @Test
    public void testRaiseBetweenOneAndInfinity() {
        assertAll(
                () -> assertTrue(lnFunction.ln(1) < lnFunction.ln(2)),
                () -> assertTrue(lnFunction.ln(2) < lnFunction.ln(5)),
                () -> assertTrue(lnFunction.ln(5) < lnFunction.ln(10))
        );
    }

}
